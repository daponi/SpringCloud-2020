package com.atguigu.www;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 使用Consul作为注册中心的消费者Demo
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class OrderConsulMain80
{
    public static void main(String[] args)
    {
        SpringApplication.run(OrderConsulMain80.class,args);
    }
}

