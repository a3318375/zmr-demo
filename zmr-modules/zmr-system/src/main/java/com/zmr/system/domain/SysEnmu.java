package com.zmr.system.domain;


import com.mybatisflex.annotation.Table;
import com.zmr.common.mybatis.dto.ZmrBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 常量字段表(SysEnmu)实体类
 *
 * @author makejava
 * @since 2026-04-20 16:44:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table("sys_enmu")
public class SysEnmu extends ZmrBaseDTO {
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类编码
     */
    private String code;
    /**
     * 排序
     */
    private Integer sort;

}

