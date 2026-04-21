package com.zmr.system.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.zmr.common.mybatis.utils.QueryWrapperUtils;
import com.zmr.system.domain.SysEnmu;
import com.zmr.system.mapper.SysEnmuMapper;
import com.zmr.common.core.utils.IdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 常量字段表(SysEnmu)表服务实现类
 *
 * @author makejava
 * @since 2026-04-20 16:40:59
 */
@Service
public class SysEnmuService {

    @Resource
    private SysEnmuMapper sysEnmuMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SysEnmu detail(Long id) {
        return sysEnmuMapper.selectOneById(id);
    }

    /**
     * 不分页查询
     *
     * @param sysEnmu 筛选条件
     * @return 查询结果
     */
    public List<SysEnmu> list(SysEnmu sysEnmu) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysEnmu);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysEnmu);
        return sysEnmuMapper.selectListByQuery(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param sysEnmu 筛选条件
     * @return 查询结果
     */
    public Page<SysEnmu> page(SysEnmu sysEnmu) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysEnmu);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysEnmu);
        return sysEnmuMapper.paginate(sysEnmu.getPageNum(), sysEnmu.getPageSize(), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param sysEnmu 实例对象
     * @return 实例对象
     */
    @Transactional
    public int add(SysEnmu sysEnmu) {
        int sort = sysEnmuMapper.getMaxSort();
        sysEnmu.setSort(sort + 1);
        sysEnmu.setId(IdGenerator.generateId());
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysEnmu.setCreateBy(user.getUserid());
        sysEnmu.setDeleted(0);
        sysEnmu.setCreateTime(LocalDateTime.now());
        return sysEnmuMapper.insert(sysEnmu);
    }

    /**
     * 修改数据
     *
     * @param sysEnmu 实例对象
     * @return 实例对象
     */
    @Transactional
    public int edit(SysEnmu sysEnmu) {
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysEnmu.setUpdateBy(user.getUserid());
        sysEnmu.setUpdateTime(LocalDateTime.now());
        return sysEnmuMapper.update(sysEnmu);

    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    public int remove(Long id) {
        return sysEnmuMapper.deleteById(id);
    }

    /**
     * 通过主键批量删除数据
     *
     * @param idList 主键集合
     * @return 是否成功
     */
    @Transactional
    public int removeBatch(List<Long> idList) {
        return sysEnmuMapper.deleteBatchByIds(idList);
    }
}
