package com.atguigu.www.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * openFeign的日志打印功能
 */
@Configuration
public class FeignConfig
{
    @Bean
    Logger.Level feignLoggerLevel()
    {
        //feign日志以什么级别监控哪个接口
        return Logger.Level.FULL;
    }
}