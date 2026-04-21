package com.zmr.auth.service.impl;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.zmr.auth.service.ValidateCaptchaService;
import com.zmr.common.core.constant.CacheConstants;
import com.zmr.common.core.exception.CaptchaException;
import com.zmr.common.core.utils.StringUtils;
import com.zmr.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * 验证码实现处理
 *
 * @author zmr-os
 */
@Service
public class ValidateCaptchaServiceImpl implements ValidateCaptchaService {

    @Autowired
    private RedisService redisService;

    @Autowired
    @Lazy
    private CaptchaService captchaService;


    /**
     * 校验验证码
     */
    @Override
    public void checkCaptcha(String code, String uuid) throws CaptchaException {
        if (StringUtils.isEmpty(code)) {
            throw new CaptchaException("验证码不能为空");
        }
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisService.getCacheObject(verifyKey);
        if (captcha == null) {
            throw new CaptchaException("验证码已失效");
        }
        redisService.deleteObject(verifyKey);
        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException("验证码错误");
        }
    }

    /**
     * 校验验证码--滑块
     */
    @Override
    public void checkAjCaptcha(String code, String uuid) throws CaptchaException {
        if (StringUtils.isEmpty(code)) {
            throw new CaptchaException("验证码不能为空");
        }
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaVerification(code);
        ResponseModel response = captchaService.verification(captchaVO);
        if (!response.isSuccess()) {
            throw new CaptchaException("验证码错误");
        }
    }
}
