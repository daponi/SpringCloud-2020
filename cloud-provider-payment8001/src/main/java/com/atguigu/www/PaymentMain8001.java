package com.atguigu.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 使用eureka作为注册中心的客户端Demo
 */
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient//该注解用于向使用consul或者zookeeper、Nacos作为注册中心时注册服务
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
