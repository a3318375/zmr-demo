package com.zmr.system.controller;

import com.mybatisflex.core.paginate.Page;
import com.zmr.system.domain.SysRole;
import com.zmr.system.service.SysRoleService;
import com.zmr.common.mybatis.web.BasePlusController;
import com.zmr.common.core.web.domain.AjaxResult;
import com.zmr.common.core.web.domain.TableDataInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色信息表(SysRole)表控制层
 *
 * @author makejava
 * @since 2026-04-20 16:41:00
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController extends BasePlusController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 不分页查询
     *
     * @param sysRole 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public AjaxResult list(SysRole sysRole) {
        return success(sysRoleService.list(sysRole));
    }

    /**
     * 分页查询
     *
     * @param sysRole 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public TableDataInfo page(SysRole sysRole) {
        Page<SysRole> list = sysRoleService.page(sysRole);
        return getDataTable(list);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param sysRole id 主键
     * @return 单条数据
     */
    @GetMapping("/detail")
    public AjaxResult detail(SysRole sysRole) {
        return success(sysRoleService.detail(sysRole.getId()));
    }

    /**
     * 新增数据
     *
     * @param sysRole 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysRole sysRole) {
        return toAjax(sysRoleService.add(sysRole));
    }

    /**
     * 编辑数据
     *
     * @param sysRole 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SysRole sysRole) {
        return toAjax(sysRoleService.edit(sysRole));
    }

    /**
     * 删除数据
     *
     * @param sysRole id 主键
     * @return 删除是否成功
     */
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody SysRole sysRole) {
        return toAjax(sysRoleService.remove(sysRole.getId()));
    }

    /**
     * 批量删除
     *
     * @param sysRole ids 主键数组
     * @return 删除是否成功
     */
    @PostMapping("/removeBatch")
    public AjaxResult removeBatch(@RequestBody SysRole sysRole) {
        return toAjax(sysRoleService.removeBatch(sysRole.getIds()));
    }
}

