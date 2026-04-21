package com.zmr.system.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zmr.system.domain.SysEnmu;

import java.util.List;

/**
 * 常量字段表(SysEnmu)表数据库访问层
 *
 * @author makejava
 * @since 2026-04-20 16:40:59
 */
public interface SysEnmuMapper extends BaseMapper<SysEnmu> {

    /**
     * 获取最大序号
     *
     * @return 最大序号
     */
    int getMaxSort();
}

