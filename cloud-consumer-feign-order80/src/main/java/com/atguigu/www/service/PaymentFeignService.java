package com.atguigu.www.service;

import com.atguigu.www.config.FeignConfig;
import com.atguigu.www.entities.CommonResult;
import com.atguigu.www.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component  //方便直接自动装配，用于测试快速看效果
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);


    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeOut();
}
