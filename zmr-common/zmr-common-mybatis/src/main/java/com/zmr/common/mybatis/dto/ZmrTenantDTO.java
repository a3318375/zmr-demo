package com.zmr.common.mybatis.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author: Aizmr
 * @CreateTime: 2024-05-23
 * @Description: 组织DTO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ZmrTenantDTO extends ZmrBaseDTO {

    /**
     * 租戶id
     */
    private Long tenantId;

}
