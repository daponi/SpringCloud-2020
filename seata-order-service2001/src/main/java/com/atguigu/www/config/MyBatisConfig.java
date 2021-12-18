package com.atguigu.www.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.atguigu.www.dao"})
public class MyBatisConfig {
}
