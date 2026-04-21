package com.zmr.system.domain;


import com.mybatisflex.annotation.Table;
import com.zmr.common.mybatis.dto.ZmrBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 角色和菜单关联表(SysRoleMenu)实体类
 *
 * @author makejava
 * @since 2026-04-20 16:44:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table("sys_role_menu")
public class SysRoleMenu extends ZmrBaseDTO {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;

}

