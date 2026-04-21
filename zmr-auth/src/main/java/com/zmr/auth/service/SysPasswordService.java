package com.zmr.auth.service;

import com.zmr.auth.dto.LoginBody;
import com.zmr.auth.dto.SysUserDTO;
import com.zmr.common.core.constant.CacheConstants;
import com.zmr.common.core.exception.ServiceException;
import com.zmr.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 登录密码方法
 *
 * @author zmr-os
 */
@Component
public class SysPasswordService {
    @Autowired
    private RedisService redisService;

    private int maxRetryCount = CacheConstants.PASSWORD_MAX_RETRY_COUNT;

    private Long lockTime = CacheConstants.PASSWORD_LOCK_TIME;

    @Autowired
    private ValidateCaptchaService validateCaptchaService;

    /**
     * 登录账户密码错误次数缓存键名
     *
     * @param username 用户名
     * @return 缓存键key
     */
    private String getCacheKey(String username) {
        return CacheConstants.PWD_ERR_CNT_KEY + username;
    }

    public void validate(String username, SysUserDTO user, LoginBody form) {
        Integer retryCount = redisService.getCacheObject(getCacheKey(username));

        if (retryCount == null) {
            retryCount = 0;
        }

        if (retryCount >= Integer.valueOf(maxRetryCount).intValue()) {
            if (form.getType() != null && form.getType() == 1) {
                validateCaptchaService.checkCaptcha(form.getCode(), form.getUuid());
            } else {
                validateCaptchaService.checkAjCaptcha(form.getCode(), form.getUuid());
            }
        }

        if (!matches(user, form.getPassword())) {
            retryCount = retryCount + 1;
            redisService.setCacheObject(getCacheKey(username), retryCount, lockTime, TimeUnit.MINUTES);
            throw new ServiceException("用户不存在/密码错误");
        } else {
            clearLoginRecordCache(username);
        }
    }

    public boolean matches(SysUserDTO user, String rawPassword) {
        return BCrypt.checkpw(rawPassword, user.getPassword());
    }

    public void clearLoginRecordCache(String loginName) {
        if (redisService.hasKey(getCacheKey(loginName))) {
            redisService.deleteObject(getCacheKey(loginName));
        }
    }

    public int passwordErrNum(String account) {
        Integer num = redisService.getCacheObject(getCacheKey(account));
        if (num == null) {
            num = 0;
        }
        return num;
    }
}
