package com.zmr.system.controller;

import com.mybatisflex.core.paginate.Page;
import com.zmr.system.domain.SysEnmu;
import com.zmr.system.service.SysEnmuService;
import com.zmr.common.mybatis.web.BasePlusController;
import com.zmr.common.core.web.domain.AjaxResult;
import com.zmr.common.core.web.domain.TableDataInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 常量字段表(SysEnmu)表控制层
 *
 * @author makejava
 * @since 2026-04-20 16:40:59
 */
@RestController
@RequestMapping("/sysEnmu")
public class SysEnmuController extends BasePlusController {

    @Resource
    private SysEnmuService sysEnmuService;

    /**
     * 不分页查询
     *
     * @param sysEnmu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public AjaxResult list(SysEnmu sysEnmu) {
        return success(sysEnmuService.list(sysEnmu));
    }

    /**
     * 分页查询
     *
     * @param sysEnmu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public TableDataInfo page(SysEnmu sysEnmu) {
        Page<SysEnmu> list = sysEnmuService.page(sysEnmu);
        return getDataTable(list);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param sysEnmu id 主键
     * @return 单条数据
     */
    @GetMapping("/detail")
    public AjaxResult detail(SysEnmu sysEnmu) {
        return success(sysEnmuService.detail(sysEnmu.getId()));
    }

    /**
     * 新增数据
     *
     * @param sysEnmu 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysEnmu sysEnmu) {
        return toAjax(sysEnmuService.add(sysEnmu));
    }

    /**
     * 编辑数据
     *
     * @param sysEnmu 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SysEnmu sysEnmu) {
        return toAjax(sysEnmuService.edit(sysEnmu));
    }

    /**
     * 删除数据
     *
     * @param sysEnmu id 主键
     * @return 删除是否成功
     */
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody SysEnmu sysEnmu) {
        return toAjax(sysEnmuService.remove(sysEnmu.getId()));
    }

    /**
     * 批量删除
     *
     * @param sysEnmu ids 主键数组
     * @return 删除是否成功
     */
    @PostMapping("/removeBatch")
    public AjaxResult removeBatch(@RequestBody SysEnmu sysEnmu) {
        return toAjax(sysEnmuService.removeBatch(sysEnmu.getIds()));
    }
}

