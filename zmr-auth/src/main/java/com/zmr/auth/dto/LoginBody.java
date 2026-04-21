package com.zmr.auth.dto;

import lombok.Data;

/**
 * 用户登录对象
 * @author zmr-os
 */
@Data
public class LoginBody {

    /**
     * 用户名
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证码
     */
    private String code;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 1-字母验证，2-滑块
     */
    private Integer type;
    /**
     * 1-密码，2-邮箱
     */
    private Integer authType = 1;

}
