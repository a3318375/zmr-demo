package com.zmr.system.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.zmr.common.mybatis.utils.QueryWrapperUtils;
import com.zmr.system.domain.SysUser;
import com.zmr.system.mapper.SysUserMapper;
import com.zmr.common.core.utils.IdGenerator;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.zmr.system.domain.table.SysUserTableDef.SYS_USER;


/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2026-04-20 16:41:01
 */
@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SysUser detail(Long id) {
        return sysUserMapper.selectOneById(id);
    }

    /**
     * 不分页查询
     *
     * @param sysUser 筛选条件
     * @return 查询结果
     */
    public List<SysUser> list(SysUser sysUser) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysUser);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysUser);
        return sysUserMapper.selectListByQuery(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param sysUser 筛选条件
     * @return 查询结果
     */
    public Page<SysUser> page(SysUser sysUser) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysUser);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysUser);
        return sysUserMapper.paginate(sysUser.getPageNum(), sysUser.getPageSize(), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Transactional
    public int add(SysUser sysUser) {
        sysUser.setId(IdGenerator.generateId());
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysUser.setCreateBy(user.getUserid());
        sysUser.setDeleted(0);
        sysUser.setCreateTime(LocalDateTime.now());
        return sysUserMapper.insert(sysUser);
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Transactional
    public int edit(SysUser sysUser) {
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysUser.setUpdateBy(user.getUserid());
        sysUser.setUpdateTime(LocalDateTime.now());
        return sysUserMapper.update(sysUser);

    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    public int remove(Long id) {
        return sysUserMapper.deleteById(id);
    }

    /**
     * 通过主键批量删除数据
     *
     * @param idList 主键集合
     * @return 是否成功
     */
    @Transactional
    public int removeBatch(List<Long> idList) {
        return sysUserMapper.deleteBatchByIds(idList);
    }

    /**
     * 根据账号查询
     */
    public SysUser getByAccount(SysUser sysUser) {
        QueryWrapper queryWrapper = QueryWrapper.create().where(SYS_USER.ACCOUNT.eq(sysUser.getAccount()));
        return sysUserMapper.selectOneByQuery(queryWrapper);
    }

    /**
     * 根据邮箱查询
     */
    public SysUser getByEmail(SysUser sysUser) {
        QueryWrapper queryWrapper = QueryWrapper.create().where(SYS_USER.EMAIL.eq(sysUser.getEmail()));
        return sysUserMapper.selectOneByQuery(queryWrapper);
    }
}
