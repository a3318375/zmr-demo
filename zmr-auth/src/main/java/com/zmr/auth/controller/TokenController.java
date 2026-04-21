package com.zmr.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.zmr.auth.dto.LoginBody;
import com.zmr.auth.service.SysLoginService;
import com.zmr.common.core.domain.R;
import com.zmr.common.core.web.controller.BaseController;
import com.zmr.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * token 控制
 *
 * @author zmr-os
 */
@RestController
public class TokenController extends BaseController {

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody form) {
        return toAjax(sysLoginService.login(form));
    }

    @DeleteMapping("/logout")
    public R<?> logout() {
        StpUtil.logout();
        return R.ok();
    }

    @GetMapping("/passwordErrNum")
    public R<?> passwordErrNum(String account) {
        return R.ok(sysLoginService.passwordErrNum(account));
    }
}
