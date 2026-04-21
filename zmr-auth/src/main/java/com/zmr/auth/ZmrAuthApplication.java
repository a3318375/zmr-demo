package com.zmr.auth;

import com.zmr.common.core.web.controller.RestTemplateConfig;
import com.zmr.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * 认证授权中心
 *
 * @author zmr-os
 */
@EnableRyFeignClients
@Import({RestTemplateConfig.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ZmrAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZmrAuthApplication.class, args);
    }
}
