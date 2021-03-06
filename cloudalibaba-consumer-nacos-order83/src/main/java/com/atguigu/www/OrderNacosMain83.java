package com.atguigu.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * alibaba nacos discovery的消费者Demo
 */
@SpringBootApplication
@EnableDiscoveryClient//该注解用于向使用consul或者zookeeper、Nacos作为注册中心时注册服务
public class OrderNacosMain83 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain83.class, args);
    }
}
