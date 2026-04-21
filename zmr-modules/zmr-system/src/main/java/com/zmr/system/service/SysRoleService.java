package com.zmr.system.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.zmr.common.mybatis.utils.QueryWrapperUtils;
import com.zmr.system.domain.SysRole;
import com.zmr.system.mapper.SysRoleMapper;
import com.zmr.common.core.utils.IdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.zmr.system.domain.table.SysRoleTableDef.SYS_ROLE;

/**
 * 角色信息表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2026-04-20 18:29:30
 */
@Service
public class SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SysRole detail(Long id) {
        return sysRoleMapper.selectOneById(id);
    }

    /**
     * 不分页查询
     *
     * @param sysRole 筛选条件
     * @return 查询结果
     */
    public List<SysRole> list(SysRole sysRole) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysRole)
                .where(SYS_ROLE.NAME.like(sysRole.getName()));
        QueryWrapperUtils.addOrderBy(queryWrapper, sysRole);
        return sysRoleMapper.selectListByQuery(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param sysRole 筛选条件
     * @return 查询结果
     */
    public Page<SysRole> page(SysRole sysRole) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysRole);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysRole);
        return sysRoleMapper.paginate(sysRole.getPageNum(), sysRole.getPageSize(), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Transactional
    public int add(SysRole sysRole) {
        int sort = sysRoleMapper.getMaxSort();
        sysRole.setSort(sort + 1);
        sysRole.setId(IdGenerator.generateId());
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysRole.setCreateBy(user.getUserid());
        sysRole.setDeleted(0);
        sysRole.setCreateTime(LocalDateTime.now());
        return sysRoleMapper.insert(sysRole);
    }

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Transactional
    public int edit(SysRole sysRole) {
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysRole.setUpdateBy(user.getUserid());
        sysRole.setUpdateTime(LocalDateTime.now());
        return sysRoleMapper.update(sysRole);

    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    public int remove(Long id) {
        return sysRoleMapper.deleteById(id);
    }

    /**
     * 通过主键批量删除数据
     *
     * @param idList 主键集合
     * @return 是否成功
     */
    @Transactional
    public int removeBatch(List<Long> idList) {
        return sysRoleMapper.deleteBatchByIds(idList);
    }
}
