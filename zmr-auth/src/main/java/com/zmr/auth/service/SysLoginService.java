package com.zmr.auth.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.zmr.auth.dto.LoginBody;
import com.zmr.auth.dto.SysUserDTO;
import com.zmr.auth.feign.RemoteSystemService;
import com.zmr.common.core.constant.CacheConstants;
import com.zmr.common.core.constant.SecurityConstants;
import com.zmr.common.core.constant.UserConstants;
import com.zmr.common.core.domain.R;
import com.zmr.common.core.exception.ServiceException;
import com.zmr.common.core.text.Convert;
import com.zmr.common.core.utils.StringUtils;
import com.zmr.common.core.utils.ip.IpUtils;
import com.zmr.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录校验方法
 *
 * @author zmr-os
 */
@Component
public class SysLoginService {

    @Autowired
    private RemoteSystemService remoteSystemService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private RedisService redisService;

    /**
     * 登录
     */
    public Map<String, Object> login(LoginBody form) {
        Map<String, Object> resultMap = new HashMap<>();
        if (form.getAuthType() == 1) {
            // 用户名或密码为空 错误
            if (StringUtils.isAnyBlank(form.getAccount(), form.getPassword())) {
                throw new ServiceException("用户/密码必须填写");
            }
            // 用户名不在指定范围内 错误
            if (form.getAccount().length() < UserConstants.USERNAME_MIN_LENGTH
                    || form.getAccount().length() > UserConstants.USERNAME_MAX_LENGTH) {
                throw new ServiceException("用户名不在指定范围");
            }
        } else {
            if (StringUtils.isAnyBlank(form.getEmail(), form.getPassword())) {
                throw new ServiceException("邮箱/密码必须填写");
            }
        }

        // 密码如果不在指定范围内 错误
        if (form.getPassword().length() < UserConstants.PASSWORD_MIN_LENGTH
                || form.getPassword().length() > UserConstants.PASSWORD_MAX_LENGTH) {
            throw new ServiceException("用户密码不在指定范围");
        }
        // IP黑名单校验
        String blackStr = Convert.toStr(redisService.getCacheObject(CacheConstants.SYS_LOGIN_BLACKIPLIST));
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr())) {
            throw new ServiceException("很遗憾，访问IP已被列入系统黑名单");
        }

        Map<String, Object> accountParams = new HashMap<>();
        R<SysUserDTO> userResult = null;

        if (form.getAuthType() == 1) {
            accountParams.put("account", form.getAccount());
            userResult = remoteSystemService.getByAccount(accountParams, SecurityConstants.INNER);
        } else {
            accountParams.put("email", form.getEmail());
            userResult = remoteSystemService.getByEmail(accountParams, SecurityConstants.INNER);
        }

        if (R.FAIL == userResult.getCode()) {
            throw new ServiceException(userResult.getMsg());
        }
        if (StringUtils.isNull(userResult.getData())) {
            throw new ServiceException(userResult.getMsg());
        }
        resultMap.put("user", userResult.getData());
        passwordService.validate(form.getEmail(), userResult.getData(), form);
        StpUtil.login(userResult.getData().getId());
        // 第2步，获取 Token  相关参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        resultMap.put("token", tokenInfo);
        return resultMap;
    }

    public void logout(String loginName) {
    }

    public int passwordErrNum(String account) {
        return passwordService.passwordErrNum(account);
    }
}
