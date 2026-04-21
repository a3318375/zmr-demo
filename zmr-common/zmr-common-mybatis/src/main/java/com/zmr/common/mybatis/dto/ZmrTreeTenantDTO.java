package com.zmr.common.mybatis.dto;

import com.mybatisflex.annotation.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aizmr
 * @createtime 2024-05-23
 * @Description 组织DTO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ZmrTreeTenantDTO extends ZmrTenantDTO {

    /**
     * 子集合
     */
    @Column(ignore = true)
    private List<?> children = new ArrayList<>();

    /**
     * 父ID
     */
    private Long parentId;
}
