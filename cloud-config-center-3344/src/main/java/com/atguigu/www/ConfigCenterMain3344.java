package com.atguigu.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 演示SpringCloud Config服务端的Demo
 */
@SpringBootApplication
@EnableConfigServer //启动springcloud-config-server
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class,  args);
    }
}
