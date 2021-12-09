package com.atguigu.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 演示Hystrix作为服务降级的提供者Demo
 */

@SpringBootApplication
@EnableEurekaClient//本服务启动后会自动注册进eureka服务中
@EnableCircuitBreaker//现在有个@springcloudapplication注解里面带着这个注解
public class PaymentHystrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class, args);
    }
}
