package com.atguigu.www.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.www.service.PaymentService;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    /**
     * 正常访问，一切OK
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池:"+Thread.currentThread().getName()+" ,paymentInfo_OK,id: "+id+"\t"+"O(∩_∩)O";
    }

    /**
     * 模拟超时访问，演示降级
     * HystrixCommand的属性可在HystrixCommandProperties类找
     * 服务降级的fallbackMethod的参数、返回值需要和调用函数一样，不能省略
     * 若因超时调用fallback则有线程隔离(不是同一个线程)，若是抛异常则是同一个线程
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    @Override
    public String paymentInfo_TimeOut(Integer id) {

        int timeout=2;
//        int a=10/0;
        try { TimeUnit.SECONDS.sleep(timeout); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池:"+Thread.currentThread().getName()+" ,paymentInfo_TimeOut,id: "+id+"\t"+"O(∩_∩)O，耗费"+timeout+"秒";
    }

    //降级函数
    public String paymentInfo_TimeOutHandler(Integer id){
        return "/(ㄒoㄒ)/调用支付接口超时或异常：\t"+ "\t当前线程池名字:" + Thread.currentThread().getName();
    }

    /*==========================服务熔断===========================*/
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求量阈值，此属性设置将触发电路的滚动窗口中的最小请求数。例如，如果值为20，那么如果在滚动窗口(比如10秒的窗口)中只收到19个请求，即使所有19个请求都失败，电路也不会触发打开。
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),// 休眠窗，这个属性设置熔断器打开后拒绝请求的时间量，经过这个时间量后允许再次尝试以确定电路是否应该再次关闭。
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 错误率，失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();//hutool包的id生成器

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }


}
