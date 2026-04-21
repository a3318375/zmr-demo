package com.zmr.system.mapper;

import com.mybatisflex.core.BaseMapper;
import com.zmr.system.domain.SysConfig;

import java.util.List;

/**
 * 参数配置表(SysConfig)表数据库访问层
 *
 * @author makejava
 * @since 2026-04-20 16:41:02
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    /**
     * 获取最大序号
     *
     * @return 最大序号
     */
    int getMaxSort();
}

