package com.zmr.auth.service;

import com.zmr.common.core.exception.CaptchaException;

/**
 * 验证码处理
 *
 * @author zmr-os
 */
public interface ValidateCaptchaService {
    /**
     * 校验验证码
     */
    public void checkCaptcha(String code, String uuid) throws CaptchaException;



    public void checkAjCaptcha(String code, String uuid) throws CaptchaException;
}
