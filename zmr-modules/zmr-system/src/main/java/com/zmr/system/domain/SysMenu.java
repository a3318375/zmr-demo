package com.zmr.system.domain;


import com.mybatisflex.annotation.Table;
import com.zmr.common.mybatis.dto.ZmrTreeDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 菜单权限表(SysMenu)实体类
 *
 * @author makejava
 * @since 2026-04-20 16:44:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table("sys_menu")
public class SysMenu extends ZmrTreeDTO {
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 父菜单ID
     */
    private Long parentId;
    /**
     * 显示顺序
     */
    private Integer sort;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 路由参数
     */
    private String query;
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String type;
    /**
     * 菜单状态（1正常 2停用）
     */
    private String status;
    /**
     * 叶子节点（1是2不是）
     */
    private Integer leaf;
    /**
     * 权限标识
     */
    private String code;
    /**
     * 是否公共
     */
    private Integer isPublic;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 备注
     */
    private String remark;

}

