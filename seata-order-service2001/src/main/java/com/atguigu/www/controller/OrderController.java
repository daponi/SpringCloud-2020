package com.atguigu.www.controller;

import com.atguigu.www.domain.CommonResult;
import com.atguigu.www.domain.Order;
import com.atguigu.www.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * Order是暴露给客户端下订单用的，本例子没有前端浏览器只用Get方式传参，但底层还是用了POST方式
     * @param order
     * @return
     */
    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功！");
    }
}
