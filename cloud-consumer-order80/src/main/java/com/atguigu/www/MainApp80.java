package com.atguigu.www;

import com.atguigu.www.myRule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 使用Eureka作为注册中心的消费者Demo
 */
@EnableEurekaClient
@SpringBootApplication
//启动该微服务时，去加载我们的自定义Ribbon配置类，从而使MySelfRule配置生效，
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration= MySelfRule.class)
public class MainApp80 {
    public static void main(String[] args) {
        SpringApplication.run(MainApp80.class, args);
    }
}
