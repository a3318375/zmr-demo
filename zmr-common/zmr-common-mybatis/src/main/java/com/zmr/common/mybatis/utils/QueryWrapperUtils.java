package com.zmr.common.mybatis.utils;

import com.mybatisflex.core.query.QueryWrapper;
import com.zmr.common.core.utils.StringUtils;
import com.zmr.common.mybatis.dto.ZmrBaseDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Aizmr
 * @CreateTime: 2026-01-15
 * @Description:
 */
public class QueryWrapperUtils {

    /**
     * 安全地添加排序条件（带白名单）
     */
    public static <T> QueryWrapper addOrderBy(QueryWrapper wrapper, ZmrBaseDTO dto) {
        if (StringUtils.isNotNull(dto)) {
            if (StringUtils.isNotEmpty(dto.getIdStr())) {
                List<Long> idList = Arrays.stream(dto.getIdStr().split(","))
                        .map(Long::valueOf) // valueOf 内部有 LongCache，parseLong 则每次返回新对象
                        .collect(Collectors.toList());

                wrapper.in("id", idList);
            }
            if (StringUtils.isNotNull(dto.getLimitSize()) && dto.getLimitSize() > 0) {
                int realLimit = Math.min(dto.getLimitSize(), 1000); // 安全兜底
                wrapper.limit(realLimit);
            }

            if (StringUtils.isNotEmpty(dto.getOrderField())) {
//                if (StringUtils.isNotEmpty(dto.getOrderType()) && "DESC".equals(dto.getOrderType())) {
//                    wrapper.orderByDesc(dto.getOrderField().replace("t.", ""));
//                } else {
//                    wrapper.orderByAsc(dto.getOrderField().replace("t.", ""));
//                }
            }
        }
        return wrapper;
    }
}
