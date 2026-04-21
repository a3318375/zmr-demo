package com.zmr.system.controller;

import com.mybatisflex.core.paginate.Page;
import com.zmr.system.domain.SysUserRole;
import com.zmr.system.service.SysUserRoleService;
import com.zmr.common.mybatis.web.BasePlusController;
import com.zmr.common.core.web.domain.AjaxResult;
import com.zmr.common.core.web.domain.TableDataInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户和角色关联表(SysUserRole)表控制层
 *
 * @author makejava
 * @since 2026-04-20 16:41:01
 */
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController extends BasePlusController {

    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
     * 不分页查询
     *
     * @param sysUserRole 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public AjaxResult list(SysUserRole sysUserRole) {
        return success(sysUserRoleService.list(sysUserRole));
    }

    /**
     * 分页查询
     *
     * @param sysUserRole 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public TableDataInfo page(SysUserRole sysUserRole) {
        Page<SysUserRole> list = sysUserRoleService.page(sysUserRole);
        return getDataTable(list);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param sysUserRole id 主键
     * @return 单条数据
     */
    @GetMapping("/detail")
    public AjaxResult detail(SysUserRole sysUserRole) {
        return success(sysUserRoleService.detail(sysUserRole.getId()));
    }

    /**
     * 新增数据
     *
     * @param sysUserRole 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysUserRole sysUserRole) {
        return toAjax(sysUserRoleService.add(sysUserRole));
    }

    /**
     * 编辑数据
     *
     * @param sysUserRole 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SysUserRole sysUserRole) {
        return toAjax(sysUserRoleService.edit(sysUserRole));
    }

    /**
     * 删除数据
     *
     * @param sysUserRole id 主键
     * @return 删除是否成功
     */
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody SysUserRole sysUserRole) {
        return toAjax(sysUserRoleService.remove(sysUserRole.getId()));
    }

    /**
     * 批量删除
     *
     * @param sysUserRole ids 主键数组
     * @return 删除是否成功
     */
    @PostMapping("/removeBatch")
    public AjaxResult removeBatch(@RequestBody SysUserRole sysUserRole) {
        return toAjax(sysUserRoleService.removeBatch(sysUserRole.getIds()));
    }
}

