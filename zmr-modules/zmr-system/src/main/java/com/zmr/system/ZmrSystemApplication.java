package com.zmr.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.zmr.common.security.annotation.EnableCustomConfig;
import com.zmr.common.security.annotation.EnableRyFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 系统模块
 *
 * @author zmr-os
 */
@EnableCustomConfig
// 指定要扫描的Mapper类的包的路径
@MapperScan("com.zmr.system.mapper")
@EnableRyFeignClients
@EnableScheduling
@SpringBootApplication
public class ZmrSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZmrSystemApplication.class, args);
    }
}
