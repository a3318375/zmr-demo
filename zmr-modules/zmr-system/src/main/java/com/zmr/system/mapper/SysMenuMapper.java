package com.zmr.system.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zmr.system.domain.SysMenu;

import java.util.List;

/**
 * 菜单权限表(SysMenu)表数据库访问层
 *
 * @author makejava
 * @since 2026-04-20 16:41:00
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 获取最大序号
     *
     * @return 最大序号
     */
    int getMaxSort();
}

