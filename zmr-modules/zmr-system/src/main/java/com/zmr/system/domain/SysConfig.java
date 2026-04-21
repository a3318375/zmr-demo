package com.zmr.system.domain;


import com.mybatisflex.annotation.Table;
import com.zmr.common.mybatis.dto.ZmrBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 参数配置表(SysConfig)实体类
 *
 * @author makejava
 * @since 2026-04-20 16:44:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Table("sys_config")
public class SysConfig extends ZmrBaseDTO {
    /**
     * 参数名称
     */
    private String configName;
    /**
     * 参数键名
     */
    private String configKey;
    /**
     * 参数键值
     */
    private String configValue;
    /**
     * 参数类型
     */
    private String configType;
    /**
     * 序号
     */
    private Integer sort;
    /**
     * 资源库_fk
     */
    private Long resLibraryFk;

}

