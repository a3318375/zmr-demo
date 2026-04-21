package com.zmr.common.mybatis.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Aizmr
 * @CreateTime: 2024-05-23
 * @Description: 组织DTO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ZmrBaseDTO {

    /** 
     * 主键 
     */
    @NotNull(message = "id不能为空", groups = {Update.class, Delete.class})
    @Id(keyType = KeyType.None)
    private Long id;

    /** 
     * 删除标志 
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer deleted;

    /** 
     * 创建人 
     */
    private String createBy;

    /** 
     * 更新人 
     */
    private String updateBy;

    /** 
     * 创建时间 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /** 
     * 更新时间 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /** 
     * 主键集合
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(ignore = true)
    private List<Long> ids;

    /** 
     * 主键集合 
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(ignore = true)
    private String idStr;

    /** 
     * 请求参数 
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(ignore = true)
    private Map<String, Object> params=new HashMap<>();

    /**
     * 分页大小 
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(ignore = true)
    private Integer pageSize;

    /**
     * 当前页 
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(ignore = true)
    private Integer pageNum;

    /** 
     * 排序自动 
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(ignore = true)
    private String orderField;

    /** 
     * 排序方式 ASC DESC 
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(ignore = true)
    private  String orderType;

    /** 
     * 取前几条，和 pageNum不兼容 
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(ignore = true)
    private Integer limitSize;
}
