package com.zmr.system.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.zmr.common.mybatis.utils.QueryWrapperUtils;
import com.zmr.system.domain.SysUserRole;
import com.zmr.system.mapper.SysUserRoleMapper;
import com.zmr.common.core.utils.IdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户和角色关联表(SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2026-04-20 16:41:02
 */
@Service
public class SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SysUserRole detail(Long id) {
        return sysUserRoleMapper.selectOneById(id);
    }

    /**
     * 不分页查询
     *
     * @param sysUserRole 筛选条件
     * @return 查询结果
     */
    public List<SysUserRole> list(SysUserRole sysUserRole) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysUserRole);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysUserRole);
        return sysUserRoleMapper.selectListByQuery(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param sysUserRole 筛选条件
     * @return 查询结果
     */
    public Page<SysUserRole> page(SysUserRole sysUserRole) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysUserRole);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysUserRole);
        return sysUserRoleMapper.paginate(sysUserRole.getPageNum(), sysUserRole.getPageSize(), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Transactional
    public int add(SysUserRole sysUserRole) {
        sysUserRole.setId(IdGenerator.generateId());
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysUserRole.setCreateBy(user.getUserid());
        sysUserRole.setDeleted(0);
        sysUserRole.setCreateTime(LocalDateTime.now());
        return sysUserRoleMapper.insert(sysUserRole);
    }

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Transactional
    public int edit(SysUserRole sysUserRole) {
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysUserRole.setUpdateBy(user.getUserid());
        sysUserRole.setUpdateTime(LocalDateTime.now());
        return sysUserRoleMapper.update(sysUserRole);

    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    public int remove(Long id) {
        return sysUserRoleMapper.deleteById(id);
    }

    /**
     * 通过主键批量删除数据
     *
     * @param idList 主键集合
     * @return 是否成功
     */
    @Transactional
    public int removeBatch(List<Long> idList) {
        return sysUserRoleMapper.deleteBatchByIds(idList);
    }
}
