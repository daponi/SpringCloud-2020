package com.atguigu.www.service;

import com.atguigu.www.domain.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}
