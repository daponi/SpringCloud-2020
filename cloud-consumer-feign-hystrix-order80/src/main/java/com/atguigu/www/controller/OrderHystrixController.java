package com.atguigu.www.controller;

import com.atguigu.www.service.PaymentHystrixService;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//默认配置的参数在HystrixCommandProperties内
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod",commandProperties = {
        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="4000")})
public class OrderHystrixController
{
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }


    /**
     * 调用指定的fallback函数,默认配置在HystrixCommandProperties类
     * @param id
     * @return
     */
    @GetMapping("/consumer/paymentInfo/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="4000")
    })
    public String paymentInfo_Hystrix_TimeOut(@PathVariable("id") Integer id)
    {
        String result = "80的线程: "+Thread.currentThread().getName()+"，服务端信息:"+paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id)
    {
        String result = "80的线程: "+Thread.currentThread().getName()+"，服务端信息:"+"我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
        return result;
    }

    //调用全局默认fallback函数
    @GetMapping("/consumer/paymentInfo/hystrix/global/timeout/{id}")
    @HystrixCommand //加了@DefaultProperties属性注解，并且没有写具体方法名字，就用统一全局的
    public String paymentInfo_Hystrix_Global_TimeOut(@PathVariable("id") Integer id)
    {
        String result = "80的线程: "+Thread.currentThread().getName()+"，服务端信息:"+paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }



    /**
     * 全局的fallback函数,全局的方法不用匹配参数
     * 全局fallback方法只能处理返回值类型相同的方法异常，这个处理不了by zero异常
     * @return
     */
    public String payment_Global_FallbackMethod()
    {

        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}

