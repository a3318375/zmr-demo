package com.zmr.auth.feign;

import com.zmr.auth.dto.SysUserDTO;
import com.zmr.common.core.constant.SecurityConstants;
import com.zmr.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * 文件服务
 *
 * @author zmr-os
 */
@FeignClient(contextId = "remoteSystemService", value = ServiceNameConstants.SYSTEM_SERVICE)
public interface RemoteSystemService {
    /**
     * 通过用户名查询用户信息
     * @param params account 用户名
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/sysUser/getByAccount")
    public R<SysUserDTO> getByAccount(@RequestBody Map<String, Object> params, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @PostMapping("/sysUser/getByEmail")
    public R<SysUserDTO> getByEmail(@RequestBody Map<String, Object> params, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
