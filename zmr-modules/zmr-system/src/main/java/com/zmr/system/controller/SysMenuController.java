package com.zmr.system.controller;

import com.mybatisflex.core.paginate.Page;
import com.zmr.system.domain.SysMenu;
import com.zmr.system.service.SysMenuService;
import com.zmr.common.mybatis.web.BasePlusController;
import com.zmr.common.core.web.domain.AjaxResult;
import com.zmr.common.core.web.domain.TableDataInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单权限表(SysMenu)表控制层
 *
 * @author makejava
 * @since 2026-04-20 16:41:00
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController extends BasePlusController {

    @Resource
    private SysMenuService sysMenuService;

    /**
     * 不分页查询
     *
     * @param sysMenu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public AjaxResult list(SysMenu sysMenu) {
        return success(sysMenuService.list(sysMenu));
    }

    /**
     * 分页查询
     *
     * @param sysMenu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public TableDataInfo page(SysMenu sysMenu) {
        Page<SysMenu> list = sysMenuService.page(sysMenu);
        return getDataTable(list);
    }

    /**
     * 树状结构
     *
     * @param sysMenu 筛选条件
     * @return 查询结果
     */
    @GetMapping("/tree")
    public AjaxResult tree(SysMenu sysMenu) {
        return success(sysMenuService.tree(sysMenu));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param sysMenu id 主键
     * @return 单条数据
     */
    @GetMapping("/detail")
    public AjaxResult detail(SysMenu sysMenu) {
        return success(sysMenuService.detail(sysMenu.getId()));
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysMenu sysMenu) {
        return toAjax(sysMenuService.add(sysMenu));
    }

    /**
     * 编辑数据
     *
     * @param sysMenu 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SysMenu sysMenu) {
        return toAjax(sysMenuService.edit(sysMenu));
    }

    /**
     * 删除数据
     *
     * @param sysMenu id 主键
     * @return 删除是否成功
     */
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody SysMenu sysMenu) {
        return toAjax(sysMenuService.remove(sysMenu.getId()));
    }

    /**
     * 批量删除
     *
     * @param sysMenu ids 主键数组
     * @return 删除是否成功
     */
    @PostMapping("/removeBatch")
    public AjaxResult removeBatch(@RequestBody SysMenu sysMenu) {
        return toAjax(sysMenuService.removeBatch(sysMenu.getIds()));
    }
}

