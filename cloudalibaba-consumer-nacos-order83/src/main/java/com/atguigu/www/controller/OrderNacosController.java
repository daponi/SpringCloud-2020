package com.atguigu.www.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
public class OrderNacosController {
    @Resource
    private RestTemplate restTemplate;

    // 使用服务地址而不写死, 通过在eureka上注册过的微服务名称调用
//    public static final String PAYMENT_URL="http://cloud-payment-service";

    //将微服务名写到了配置文件里
    @Value("${service-url.nacos-user-service}")
    private String serverURL;


    @GetMapping("/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id)
    {
        log.info("访问/consumer/payment/nacos/{}发送成功！！！",id);
        return restTemplate.getForObject(serverURL+"/payment/nacos/"+id,String.class);
    }

}
