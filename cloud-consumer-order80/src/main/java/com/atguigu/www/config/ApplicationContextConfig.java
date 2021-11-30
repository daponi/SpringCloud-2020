package com.atguigu.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    //applicationContext.xml <bean id="" class="">
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
