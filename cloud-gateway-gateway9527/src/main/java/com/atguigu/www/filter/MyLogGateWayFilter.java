package com.atguigu.www.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.util.Date;

/**
 * 自定义过滤器
 * Order是重写优先级
 */
@Slf4j
@Component
public class MyLogGateWayFilter implements GlobalFilter, Order {
    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("**************时间：{} ,执行了自定义过滤器:{}",new Date(),MyLogGateWayFilter.class);
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (username == null) {
            log.info("****************用户名为null, 无法登录！");
            //返回错误页面
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        //传递过滤链
        return chain.filter(exchange);
    }

    @Override
    public int value() {
        return 0;
    }
}
