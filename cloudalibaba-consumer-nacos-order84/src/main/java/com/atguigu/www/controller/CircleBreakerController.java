package com.atguigu.www.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.www.entities.CommonResult;
import com.atguigu.www.entities.Payment;
import com.atguigu.www.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
public class CircleBreakerController {
    //    public static final String SERVICE_URL = "http://nacos-payment-provider";
    @Value("${service-url.nacos-user-service}")
    public String SERVICE_URL;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;

    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "myFallback)   //什么都不配置
//    @SentinelResource(value = "myFallback",fallback = "handlerFallback")//只配置fallback负责业务异常
//    @SentinelResource(value = "myFallback", blockHandler = "blockHandler")//只配置blockHandler负责Sentinel配置异常
//    @SentinelResource(value = "myFallback",fallback = "handlerFallback",blockHandler = "blockHandler")//只配置blockHandler负责Sentinel配置异常
    @SentinelResource(value = "myFallback",fallback = "handlerFallback",blockHandler = "blockHandler"
            ,exceptionsToIgnore = {IllegalArgumentException.class})//只配置blockHandler负责Sentinel配置异常
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        log.info("===========成功进入/consumer/fallback/{id}");
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

    public CommonResult handlerFallback(@PathVariable Long id, Throwable throwable) {
        log.info("===========成功进入handlerFallback");
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(444, "兜底异常handlerFallback,exception内容  " + throwable.getMessage(), payment);
    }

    public CommonResult blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(445, "blockHandler-sentinel限流,无此流水: blockException  " + blockException.getMessage(), payment);

    }

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment>myPaymentSQL(@PathVariable("id") Long id){
        if (id==4){
            throw new RuntimeException("没有该id："+id);
        }
        return paymentService.paymentSQL(id);
    }


}