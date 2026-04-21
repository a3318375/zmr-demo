package com.zmr.system.controller;

import com.mybatisflex.core.paginate.Page;
import com.zmr.system.domain.SysRoleMenu;
import com.zmr.system.service.SysRoleMenuService;
import com.zmr.common.mybatis.web.BasePlusController;
import com.zmr.common.core.web.domain.AjaxResult;
import com.zmr.common.core.web.domain.TableDataInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色和菜单关联表(SysRoleMenu)表控制层
 *
 * @author makejava
 * @since 2026-04-20 16:41:00
 */
@RestController
@RequestMapping("/sysRoleMenu")
public class SysRoleMenuController extends BasePlusController {

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 不分页查询
     *
     * @param sysRoleMenu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public AjaxResult list(SysRoleMenu sysRoleMenu) {
        return success(sysRoleMenuService.list(sysRoleMenu));
    }

    /**
     * 分页查询
     *
     * @param sysRoleMenu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public TableDataInfo page(SysRoleMenu sysRoleMenu) {
        Page<SysRoleMenu> list = sysRoleMenuService.page(sysRoleMenu);
        return getDataTable(list);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param sysRoleMenu id 主键
     * @return 单条数据
     */
    @GetMapping("/detail")
    public AjaxResult detail(SysRoleMenu sysRoleMenu) {
        return success(sysRoleMenuService.detail(sysRoleMenu.getId()));
    }

    /**
     * 新增数据
     *
     * @param sysRoleMenu 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysRoleMenu sysRoleMenu) {
        return toAjax(sysRoleMenuService.add(sysRoleMenu));
    }

    /**
     * 编辑数据
     *
     * @param sysRoleMenu 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SysRoleMenu sysRoleMenu) {
        return toAjax(sysRoleMenuService.edit(sysRoleMenu));
    }

    /**
     * 删除数据
     *
     * @param sysRoleMenu id 主键
     * @return 删除是否成功
     */
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody SysRoleMenu sysRoleMenu) {
        return toAjax(sysRoleMenuService.remove(sysRoleMenu.getId()));
    }

    /**
     * 批量删除
     *
     * @param sysRoleMenu ids 主键数组
     * @return 删除是否成功
     */
    @PostMapping("/removeBatch")
    public AjaxResult removeBatch(@RequestBody SysRoleMenu sysRoleMenu) {
        return toAjax(sysRoleMenuService.removeBatch(sysRoleMenu.getIds()));
    }
}

