package com.zmr.system.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zmr.system.domain.SysEnmuValue;

import java.util.List;

/**
 * 常量字段值表(SysEnmuValue)表数据库访问层
 *
 * @author makejava
 * @since 2026-04-20 16:40:59
 */
public interface SysEnmuValueMapper extends BaseMapper<SysEnmuValue> {

    /**
     * 获取最大序号
     *
     * @return 最大序号
     */
    int getMaxSort();
}

