package com.zmr.common.security.feign;

import cn.dev33.satoken.same.SaSameUtil;
import com.zmr.common.core.utils.SecureUtil;
import com.zmr.common.core.utils.ip.IpUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * feign 请求拦截器
 *
 * @author zmr-os
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {

        // 配置客户端IP
        requestTemplate.header("X-Forwarded-For", IpUtils.getIpAddr());

        requestTemplate.header(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken());

        //-----接口加密部分
        long endTime = System.currentTimeMillis();

        Random random = new Random();
        int accessKeySecret = random.nextInt(99999 - 11111) + 1;

        String AccessKeyID = "6de1tyc5c6d8a52b131548af3085843e";
        //加密串运算
        String encryptionStr = SecureUtil.md5(AccessKeyID);
        encryptionStr = encryptionStr + SecureUtil.md5(accessKeySecret + "");
        encryptionStr = SecureUtil.md5(encryptionStr);
        encryptionStr = encryptionStr + SecureUtil.md5(endTime + "");
        encryptionStr = SecureUtil.md5(encryptionStr);

        // 添加请求头
        requestTemplate.header("Tstr", endTime + "");
        requestTemplate.header("Encry", encryptionStr);
        requestTemplate.header("Secstr", String.valueOf(accessKeySecret));
    }
}