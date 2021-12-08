package com.atguigu.www.myLoadBalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 本地手写负载均衡实现
 */
public interface MyLoadBalance {
    ServiceInstance getInstances(List<ServiceInstance> serviceInstances);
}
