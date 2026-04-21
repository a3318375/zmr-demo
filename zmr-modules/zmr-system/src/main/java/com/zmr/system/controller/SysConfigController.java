package com.zmr.system.controller;

import com.mybatisflex.core.paginate.Page;
import com.zmr.common.core.web.domain.AjaxResult;
import com.zmr.common.core.web.domain.TableDataInfo;
import com.zmr.common.mybatis.web.BasePlusController;
import com.zmr.system.domain.SysConfig;
import com.zmr.system.service.SysConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 参数配置表(SysConfig)表控制层
 *
 * @author makejava
 * @since 2026-04-20 16:41:02
 */
@RestController
@RequestMapping("/sysConfig")
public class SysConfigController extends BasePlusController {

    @Resource
    private SysConfigService sysConfigService;

    /**
     * 不分页查询
     *
     * @param sysConfig 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public AjaxResult list(SysConfig sysConfig) {
        return success(sysConfigService.list(sysConfig));
    }

    /**
     * 分页查询
     *
     * @param sysConfig 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public TableDataInfo page(SysConfig sysConfig) {
        Page<SysConfig> list = sysConfigService.page(sysConfig);
        return getDataTable(list);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param sysConfig id 主键
     * @return 单条数据
     */
    @GetMapping("/detail")
    public AjaxResult detail(SysConfig sysConfig) {
        return success(sysConfigService.detail(sysConfig.getId()));
    }

    /**
     * 新增数据
     *
     * @param sysConfig 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysConfig sysConfig) {
        return toAjax(sysConfigService.add(sysConfig));
    }

    /**
     * 编辑数据
     *
     * @param sysConfig 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SysConfig sysConfig) {
        return toAjax(sysConfigService.edit(sysConfig));
    }

    /**
     * 删除数据
     *
     * @param sysConfig id 主键
     * @return 删除是否成功
     */
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody SysConfig sysConfig) {
        return toAjax(sysConfigService.remove(sysConfig.getId()));
    }

    /**
     * 批量删除
     *
     * @param sysConfig ids 主键数组
     * @return 删除是否成功
     */
    @PostMapping("/removeBatch")
    public AjaxResult removeBatch(@RequestBody SysConfig sysConfig) {
        return toAjax(sysConfigService.removeBatch(sysConfig.getIds()));
    }
}

