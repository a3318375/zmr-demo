package com.zmr.system.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zmr.system.domain.SysRole;

import java.util.List;

/**
 * 角色信息表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2026-04-20 16:41:00
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 获取最大序号
     *
     * @return 最大序号
     */
    int getMaxSort();
}

