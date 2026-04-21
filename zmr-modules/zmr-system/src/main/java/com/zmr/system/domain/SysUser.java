package com.zmr.system.domain;


import com.mybatisflex.annotation.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import com.zmr.common.mybatis.dto.ZmrBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户信息表(SysUser)实体类
 *
 * @author makejava
 * @since 2026-04-20 16:44:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table("sys_user")
public class SysUser extends ZmrBaseDTO {
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginDate;
    /**
     * 备注
     */
    private String remark;

}

