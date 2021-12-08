package com.atguigu.www.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    //applicationContext.xml <bean id="" class="">
    @Bean
    //Springboot 2020版本 eureka删除了，ribbon可以通过@LoadBalancerClient注解来改变LoadBalancer
//    @LoadBalanced  //使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
