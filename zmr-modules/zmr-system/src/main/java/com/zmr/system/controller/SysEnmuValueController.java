package com.zmr.system.controller;

import com.mybatisflex.core.paginate.Page;
import com.zmr.system.domain.SysEnmuValue;
import com.zmr.system.service.SysEnmuValueService;
import com.zmr.common.mybatis.web.BasePlusController;
import com.zmr.common.core.web.domain.AjaxResult;
import com.zmr.common.core.web.domain.TableDataInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 常量字段值表(SysEnmuValue)表控制层
 *
 * @author makejava
 * @since 2026-04-20 16:40:59
 */
@RestController
@RequestMapping("/sysEnmuValue")
public class SysEnmuValueController extends BasePlusController {

    @Resource
    private SysEnmuValueService sysEnmuValueService;

    /**
     * 不分页查询
     *
     * @param sysEnmuValue 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public AjaxResult list(SysEnmuValue sysEnmuValue) {
        return success(sysEnmuValueService.list(sysEnmuValue));
    }

    /**
     * 分页查询
     *
     * @param sysEnmuValue 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public TableDataInfo page(SysEnmuValue sysEnmuValue) {
        Page<SysEnmuValue> list = sysEnmuValueService.page(sysEnmuValue);
        return getDataTable(list);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param sysEnmuValue id 主键
     * @return 单条数据
     */
    @GetMapping("/detail")
    public AjaxResult detail(SysEnmuValue sysEnmuValue) {
        return success(sysEnmuValueService.detail(sysEnmuValue.getId()));
    }

    /**
     * 新增数据
     *
     * @param sysEnmuValue 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysEnmuValue sysEnmuValue) {
        return toAjax(sysEnmuValueService.add(sysEnmuValue));
    }

    /**
     * 编辑数据
     *
     * @param sysEnmuValue 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SysEnmuValue sysEnmuValue) {
        return toAjax(sysEnmuValueService.edit(sysEnmuValue));
    }

    /**
     * 删除数据
     *
     * @param sysEnmuValue id 主键
     * @return 删除是否成功
     */
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody SysEnmuValue sysEnmuValue) {
        return toAjax(sysEnmuValueService.remove(sysEnmuValue.getId()));
    }

    /**
     * 批量删除
     *
     * @param sysEnmuValue ids 主键数组
     * @return 删除是否成功
     */
    @PostMapping("/removeBatch")
    public AjaxResult removeBatch(@RequestBody SysEnmuValue sysEnmuValue) {
        return toAjax(sysEnmuValueService.removeBatch(sysEnmuValue.getIds()));
    }
}

