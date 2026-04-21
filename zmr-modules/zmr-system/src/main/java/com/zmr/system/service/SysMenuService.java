package com.zmr.system.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.zmr.common.mybatis.utils.QueryWrapperUtils;
import com.zmr.system.domain.SysMenu;
import com.zmr.system.mapper.SysMenuMapper;
import com.zmr.common.core.utils.IdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.zmr.common.mybatis.utils.DtoUtils;

import java.util.List;

/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author makejava
 * @since 2026-04-20 16:43:03
 */
@Service
public class SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SysMenu detail(Long id) {
        return sysMenuMapper.selectOneById(id);
    }

    /**
     * 不分页查询
     *
     * @param sysMenu 筛选条件
     * @return 查询结果
     */
    public List<SysMenu> list(SysMenu sysMenu) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysMenu);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysMenu);
        return sysMenuMapper.selectListByQuery(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param sysMenu 筛选条件
     * @return 查询结果
     */
    public Page<SysMenu> page(SysMenu sysMenu) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysMenu);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysMenu);
        return sysMenuMapper.paginate(sysMenu.getPageNum(), sysMenu.getPageSize(), queryWrapper);
    }

    /**
     * 树状结构
     *
     * @param sysMenu 筛选条件
     * @return 查询结果
     */
    public List<SysMenu> tree(SysMenu sysMenu) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysMenu);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysMenu);
        List<SysMenu> list = sysMenuMapper.selectListByQuery(queryWrapper);
        List<SysMenu> returnList = new ArrayList<>();
        DtoUtils.recursionTree(returnList, list, null);
        return returnList;
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Transactional
    public int add(SysMenu sysMenu) {
        int sort = sysMenuMapper.getMaxSort();
        sysMenu.setSort(sort + 1);
        sysMenu.setId(IdGenerator.generateId());
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysMenu.setCreateBy(user.getUserid());
        sysMenu.setDeleted(0);
        sysMenu.setCreateTime(LocalDateTime.now());
        return sysMenuMapper.insert(sysMenu);
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Transactional
    public int edit(SysMenu sysMenu) {
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysMenu.setUpdateBy(user.getUserid());
        sysMenu.setUpdateTime(LocalDateTime.now());
        return sysMenuMapper.update(sysMenu);

    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    public int remove(Long id) {
        return sysMenuMapper.deleteById(id);
    }

    /**
     * 通过主键批量删除数据
     *
     * @param idList 主键集合
     * @return 是否成功
     */
    @Transactional
    public int removeBatch(List<Long> idList) {
        return sysMenuMapper.deleteBatchByIds(idList);
    }
}
