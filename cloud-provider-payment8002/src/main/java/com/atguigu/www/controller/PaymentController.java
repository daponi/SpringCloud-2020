package com.atguigu.www.controller;

import com.atguigu.www.entities.CommonResult;
import com.atguigu.www.entities.Payment;
import com.atguigu.www.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    //测试方法
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        log.info("*****插入操作返回结果:" + result);

        if(result > 0)
        {
            return new CommonResult(200,"插入数据库成功!port="+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败!port="+serverPort,null);
        }
    }
    //测试方法
    @GetMapping(value = "payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果:{}",payment);
        if (payment != null) {
            return new CommonResult(200,"查询成功!port="+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID: "+id+"port="+serverPort,null);
        }
    }

    //打印注册的服务实例
    @GetMapping("payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("======================serviceName : "+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("======================instance : "+instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getUri()+"\t"+instance.getPort()+"\t"+instance.getScheme());
        }
        return this.discoveryClient;
    }

    //给客户端提供演示openFeign的方法
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }

    //给客户端提供演示openFeign超时控制的方法
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut()
    {
        System.out.println("*****paymentFeignTimeOut from port: "+serverPort);
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }
}


