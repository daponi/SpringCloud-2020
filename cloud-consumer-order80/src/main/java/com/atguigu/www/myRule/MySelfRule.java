package com.atguigu.www.myRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类：
 * 用IRule的实现类来修改Eureka里Ribbon的软负载均衡方式，默认是ZoneAvoidanceRule。
 * Ribbon的软负载均衡是在客户端实现的，所以写在消费者处
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule()
    {
        return new RandomRule();//定义为随机
    }
}
