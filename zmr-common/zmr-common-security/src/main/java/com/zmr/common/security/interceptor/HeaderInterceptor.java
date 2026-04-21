package com.zmr.common.security.interceptor;

import com.zmr.common.core.constant.HttpStatus;
import com.zmr.common.core.context.SecurityContextHolder;
import com.zmr.common.core.utils.SecureUtil;
import com.zmr.common.core.utils.StringUtils;
import com.zmr.common.redis.service.RedisService;
import com.zmr.common.security.annotation.IgnoreEncry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 自定义请求头拦截器，将Header数据封装到线程变量中方便获取
 * 注意：此拦截器会同时验证当前用户有效期自动刷新有效期
 *
 * @author zmr-os
 */
@Slf4j
public class HeaderInterceptor implements AsyncHandlerInterceptor {

    private static final String apiSignKey = "apiSignKey_Str";

    @Autowired
    private RedisService redisService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;
        if (method.getMethod().isAnnotationPresent(IgnoreEncry.class)) {
            return true;
        }

        String time = request.getHeader("Tstr");
        String encry = request.getHeader("Encry");
        String AccessKeySecret = request.getHeader("Secstr");
        boolean ifPass = true;
        if (StringUtils.isNull(time) || StringUtils.isNull(encry) || StringUtils.isNull(AccessKeySecret)) {
            ifPass = false;
        } else {
            boolean verify = verify(time, encry, AccessKeySecret);
            if (!verify) {
                ifPass = false;
            }
        }
        if (!ifPass) {
            String msg = StringUtils.format("请求访问：{}，非法调用，无法访问系统资源", request.getRequestURI());
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(msg);
        }
        return ifPass;
    }

    private boolean verify(String time, String encry, String AccessKeySecret) {
        try {
            Boolean ifPass = true;
            String AccessKeyID = "6de1tyc5c6d8a52b131548af3085843e";
            //加密串运算
            String encryptionStr = SecureUtil.md5(AccessKeyID);
            encryptionStr = encryptionStr + SecureUtil.md5(AccessKeySecret);
            encryptionStr = SecureUtil.md5(encryptionStr);
            encryptionStr = encryptionStr + SecureUtil.md5(time);
            encryptionStr = SecureUtil.md5(encryptionStr);
            if (!encry.isEmpty() && encry.equals(encryptionStr)) {
                long sj = 24 * 3600 * 100;
                if (redisService.hasKey(apiSignKey + encryptionStr)) {
                    ifPass = false;
                } else {
                    long startTime = Long.parseLong(time); // 时间戳值
                    long endTime = System.currentTimeMillis();
                    //未在时间范围内
                    if (Math.abs(endTime - startTime) > sj) {
                        ifPass = false;
                    } else {
                        redisService.setCacheObject(apiSignKey + encryptionStr, time, 24L, TimeUnit.HOURS);
                    }
                }
            } else {
                ifPass = false;
            }
            return ifPass;
        } catch (Exception e) {
            log.info("验证前后端加密失败：", e);
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        SecurityContextHolder.remove();
    }
}
