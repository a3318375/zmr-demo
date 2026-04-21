package com.zmr.common.mybatis.web;

import com.mybatisflex.core.paginate.Page;
import com.zmr.common.core.constant.HttpStatus;
import com.zmr.common.core.utils.StringUtils;
import com.zmr.common.core.web.controller.BaseController;
import com.zmr.common.core.web.domain.TableDataInfo;

/**
 * web层通用数据处理
 *
 * @author zmr-os
 */
public class BasePlusController extends BaseController {

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(Page<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        if (StringUtils.isNull(list)) {

            rspData.setCode(HttpStatus.ERROR);
            rspData.setMsg("查询失败");
        } else {
            rspData.setCode(HttpStatus.SUCCESS);
            rspData.setRows(list.getRecords());
            rspData.setMsg("查询成功");
            rspData.setTotal(list.getTotalRow());
        }
        return rspData;
    }
}
