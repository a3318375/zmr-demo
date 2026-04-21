package com.zmr.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 网关启动程序
 *
 * @author Zmr-Os
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ZmrGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZmrGatewayApplication.class, args);
    }
}
