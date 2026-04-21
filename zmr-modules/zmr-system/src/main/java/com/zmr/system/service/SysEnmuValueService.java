package com.zmr.system.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.zmr.common.mybatis.utils.QueryWrapperUtils;
import com.zmr.system.domain.SysEnmuValue;
import com.zmr.system.mapper.SysEnmuValueMapper;
import com.zmr.common.core.utils.IdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 常量字段值表(SysEnmuValue)表服务实现类
 *
 * @author makejava
 * @since 2026-04-20 16:41:00
 */
@Service
public class SysEnmuValueService {

    @Resource
    private SysEnmuValueMapper sysEnmuValueMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SysEnmuValue detail(Long id) {
        return sysEnmuValueMapper.selectOneById(id);
    }

    /**
     * 不分页查询
     *
     * @param sysEnmuValue 筛选条件
     * @return 查询结果
     */
    public List<SysEnmuValue> list(SysEnmuValue sysEnmuValue) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysEnmuValue);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysEnmuValue);
        return sysEnmuValueMapper.selectListByQuery(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param sysEnmuValue 筛选条件
     * @return 查询结果
     */
    public Page<SysEnmuValue> page(SysEnmuValue sysEnmuValue) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysEnmuValue);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysEnmuValue);
        return sysEnmuValueMapper.paginate(sysEnmuValue.getPageNum(), sysEnmuValue.getPageSize(), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param sysEnmuValue 实例对象
     * @return 实例对象
     */
    @Transactional
    public int add(SysEnmuValue sysEnmuValue) {
        int sort = sysEnmuValueMapper.getMaxSort();
        sysEnmuValue.setSort(sort + 1);
        sysEnmuValue.setId(IdGenerator.generateId());
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysEnmuValue.setCreateBy(user.getUserid());
        sysEnmuValue.setDeleted(0);
        sysEnmuValue.setCreateTime(LocalDateTime.now());
        return sysEnmuValueMapper.insert(sysEnmuValue);
    }

    /**
     * 修改数据
     *
     * @param sysEnmuValue 实例对象
     * @return 实例对象
     */
    @Transactional
    public int edit(SysEnmuValue sysEnmuValue) {
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysEnmuValue.setUpdateBy(user.getUserid());
        sysEnmuValue.setUpdateTime(LocalDateTime.now());
        return sysEnmuValueMapper.update(sysEnmuValue);

    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    public int remove(Long id) {
        return sysEnmuValueMapper.deleteById(id);
    }

    /**
     * 通过主键批量删除数据
     *
     * @param idList 主键集合
     * @return 是否成功
     */
    @Transactional
    public int removeBatch(List<Long> idList) {
        return sysEnmuValueMapper.deleteBatchByIds(idList);
    }
}
