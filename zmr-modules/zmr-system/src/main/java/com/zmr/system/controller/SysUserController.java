package com.zmr.system.controller;

import com.mybatisflex.core.paginate.Page;
import com.zmr.system.domain.SysUser;
import com.zmr.system.service.SysUserService;
import com.zmr.common.mybatis.web.BasePlusController;
import com.zmr.common.core.web.domain.AjaxResult;
import com.zmr.common.core.web.domain.TableDataInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表(SysUser)表控制层
 *
 * @author makejava
 * @since 2026-04-20 16:41:01
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BasePlusController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 不分页查询
     *
     * @param sysUser 筛选条件
     * @return 查询结果
     */
    @GetMapping("/list")
    public AjaxResult list(SysUser sysUser) {
        return success(sysUserService.list(sysUser));
    }

    /**
     * 分页查询
     *
     * @param sysUser 筛选条件
     * @return 查询结果
     */
    @GetMapping("/page")
    public TableDataInfo page(SysUser sysUser) {
        Page<SysUser> list = sysUserService.page(sysUser);
        return getDataTable(list);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param sysUser id 主键
     * @return 单条数据
     */
    @GetMapping("/detail")
    public AjaxResult detail(SysUser sysUser) {
        return success(sysUserService.detail(sysUser.getId()));
    }

    /**
     * 新增数据
     *
     * @param sysUser 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.add(sysUser));
    }

    /**
     * 编辑数据
     *
     * @param sysUser 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.edit(sysUser));
    }

    /**
     * 删除数据
     *
     * @param sysUser id 主键
     * @return 删除是否成功
     */
    @PostMapping("/remove")
    public AjaxResult remove(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.remove(sysUser.getId()));
    }

    /**
     * 批量删除
     *
     * @param sysUser ids 主键数组
     * @return 删除是否成功
     */
    @PostMapping("/removeBatch")
    public AjaxResult removeBatch(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.removeBatch(sysUser.getIds()));
    }

    /**
     * 根据账号查询对象
     */
    @PostMapping("/getByAccount")
    public AjaxResult getByAccount(@RequestBody SysUser sysUser) {
        return success(sysUserService.getByAccount(sysUser));
    }

    /**
     * 根据邮箱查询对象
     */
    @PostMapping("/getByEmail")
    public AjaxResult getByEmail(@RequestBody SysUser sysUser) {
        return success(sysUserService.getByEmail(sysUser));
    }
}

