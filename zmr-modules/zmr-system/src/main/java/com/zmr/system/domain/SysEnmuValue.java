package com.zmr.system.domain;


import com.mybatisflex.annotation.Table;
import com.zmr.common.mybatis.dto.ZmrBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 常量字段值表(SysEnmuValue)实体类
 *
 * @author makejava
 * @since 2026-04-20 16:44:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table("sys_enmu_value")
public class SysEnmuValue extends ZmrBaseDTO {
    /**
     * 枚举键
     */
    private String code;
    /**
     * 枚举值
     */
    private String val;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 枚举分类_分类编码
     */
    private String zmrEnmuCode;
    /**
     * 状态（1启用2停用）
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;

}

