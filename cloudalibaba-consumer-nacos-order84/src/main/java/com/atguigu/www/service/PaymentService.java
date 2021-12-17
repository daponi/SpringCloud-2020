package com.atguigu.www.service;


import com.atguigu.www.entities.CommonResult;
import com.atguigu.www.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 使用 fallback 方式是无法获取异常信息的，
 * 如果想要获取异常信息，可以使用 fallbackFactory参数
 * 详细见官网:https://docs.spring.io/spring-cloud-openfeign/docs/2.2.9.RELEASE/reference/html/#spring-cloud-feign-hystrix-fallback
 * openFeign本身自带的hystrix进行的服务降级处理
 */
@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)//尝试先关闭生产者再调用，已测试fallback功能
public interface PaymentService {

    //生产者的接口
    @GetMapping(value = "/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
