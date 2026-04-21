package com.zmr.system.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.zmr.common.mybatis.utils.QueryWrapperUtils;
import com.zmr.system.domain.SysConfig;
import com.zmr.system.mapper.SysConfigMapper;
import com.zmr.common.core.utils.IdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 参数配置表(SysConfig)表服务实现类
 *
 * @author makejava
 * @since 2026-04-20 16:41:02
 */
@Service
public class SysConfigService {

    @Resource
    private SysConfigMapper sysConfigMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SysConfig detail(Long id) {
        return sysConfigMapper.selectOneById(id);
    }

    /**
     * 不分页查询
     *
     * @param sysConfig 筛选条件
     * @return 查询结果
     */
    public List<SysConfig> list(SysConfig sysConfig) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysConfig);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysConfig);
        return sysConfigMapper.selectListByQuery(queryWrapper);
    }

    /**
     * 分页查询
     *
     * @param sysConfig 筛选条件
     * @return 查询结果
     */
    public Page<SysConfig> page(SysConfig sysConfig) {
        QueryWrapper queryWrapper = QueryWrapper.create(sysConfig);
        QueryWrapperUtils.addOrderBy(queryWrapper, sysConfig);
        return sysConfigMapper.paginate(sysConfig.getPageNum(), sysConfig.getPageSize(), queryWrapper);
    }

    /**
     * 新增数据
     *
     * @param sysConfig 实例对象
     * @return 实例对象
     */
    @Transactional
    public int add(SysConfig sysConfig) {
        int sort = sysConfigMapper.getMaxSort();
        sysConfig.setSort(sort + 1);
        sysConfig.setId(IdGenerator.generateId());
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysConfig.setCreateBy(user.getUserid());
        sysConfig.setDeleted(0);
        sysConfig.setCreateTime(LocalDateTime.now());
        return sysConfigMapper.insert(sysConfig);
    }

    /**
     * 修改数据
     *
     * @param sysConfig 实例对象
     * @return 实例对象
     */
    @Transactional
    public int edit(SysConfig sysConfig) {
        //LoginUser user = SecurityUtils.getLoginUser();
        //sysConfig.setUpdateBy(user.getUserid());
        sysConfig.setUpdateTime(LocalDateTime.now());
        return sysConfigMapper.update(sysConfig);

    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    public int remove(Long id) {
        return sysConfigMapper.deleteById(id);
    }

    /**
     * 通过主键批量删除数据
     *
     * @param idList 主键集合
     * @return 是否成功
     */
    @Transactional
    public int removeBatch(List<Long> idList) {
        return sysConfigMapper.deleteBatchByIds(idList);
    }
}
