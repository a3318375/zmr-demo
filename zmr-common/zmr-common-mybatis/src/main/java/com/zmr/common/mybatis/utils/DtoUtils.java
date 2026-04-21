package com.zmr.common.mybatis.utils;

import com.zmr.common.core.utils.StringUtils;
import com.zmr.common.mybatis.dto.ZmrTreeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Aizmr
 * @CreateTime: 2024-07-29
 * @Description: 通用模板类
 */
public class DtoUtils {

    public static <T extends ZmrTreeDTO> void recursionTree(List<T> res, List<T> oriItems, T paretItem) {
        try {
            //第一级
            if (paretItem == null) {
                for (T oriItem : oriItems) {
                    boolean ifRoot = false;
                    if (StringUtils.isNull(oriItem.getParentId())) {
                        ifRoot = true;
                    }
                    if (!ifRoot) {
                        if (oriItem.getParentId().longValue() == -1) {
                            ifRoot = true;
                        }
                    }
                    if (ifRoot) {
                        List<T> children = new ArrayList<>();
                        oriItem.setChildren(children);
                        recursionTree(children, oriItems, oriItem);
                        res.add(oriItem);
                    }
                }
            } else {
                for (T oriItem : oriItems) {
                    if (StringUtils.isNull(oriItem.getParentId())) {
                        continue;
                    }
                    if (oriItem.getParentId().longValue() == paretItem.getId().longValue()) {
                        List<T> children = new ArrayList<>();
                        oriItem.setChildren(children);
                        recursionTree(children, oriItems, oriItem);
                        res.add(oriItem);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
