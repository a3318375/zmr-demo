package com.zmr.common.security.config;

import com.zmr.common.security.interceptor.HeaderInterceptor;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

/**
 * 系统配置
 *
 * @author zmr-os
 */
public class ApplicationConfig {
    /**
     * 时区配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }

    /**
     * 自定义请求头拦截器
     */
    @Bean
    public HeaderInterceptor getHeaderInterceptor() {
        return new HeaderInterceptor();
    }
}
