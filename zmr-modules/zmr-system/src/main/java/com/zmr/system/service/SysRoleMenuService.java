package com.zmr.system.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.zmr.common.mybatis.utils.QueryWrapperUtils;
import com.zmr.system.domain.SysRoleMenu;
import com.zmr.system.mapper.SysRoleMenuMapper;
import com.zmr.common.core.utils.IdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2026-04-20 16:41:01
 */
@Service
public class SysRoleMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SysRoleMenu detail(Long id) {
        return sysRoleMenuMapper.selectOneById(id);
    }

    /**
     * 不分页查询
     *
     * @param sysRoleMenu 筛选条件
     * @return 查询结果
     */
    public List<SysRoleMenu> list(SysRoleMenu sysRoleMenu) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysRoleMenu);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysRoleMenu);
        return sysRoleMenuMapper.selectListByQuery(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param sysRoleMenu 筛选条件
     * @return 查询结果
     */
    public Page<SysRoleMenu> page(SysRoleMenu sysRoleMenu) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysRoleMenu);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysRoleMenu);
        return sysRoleMenuMapper.paginate(sysRoleMenu.getPageNum(), sysRoleMenu.getPageSize(), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param sysRoleMenu 实例对象
     * @return 实例对象
     */
    @Transactional
    public int add(SysRoleMenu sysRoleMenu) {
        sysRoleMenu.setId(IdGenerator.generateId());
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysRoleMenu.setCreateBy(user.getUserid());
        sysRoleMenu.setDeleted(0);
        sysRoleMenu.setCreateTime(LocalDateTime.now());
        return sysRoleMenuMapper.insert(sysRoleMenu);
    }

    /**
     * 修改数据
     *
     * @param sysRoleMenu 实例对象
     * @return 实例对象
     */
    @Transactional
    public int edit(SysRoleMenu sysRoleMenu) {
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysRoleMenu.setUpdateBy(user.getUserid());
        sysRoleMenu.setUpdateTime(LocalDateTime.now());
        return sysRoleMenuMapper.update(sysRoleMenu);

    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    public int remove(Long id) {
        return sysRoleMenuMapper.deleteById(id);
    }

    /**
     * 通过主键批量删除数据
     *
     * @param idList 主键集合
     * @return 是否成功
     */
    @Transactional
    public int removeBatch(List<Long> idList) {
        return sysRoleMenuMapper.deleteBatchByIds(idList);
    }
}
