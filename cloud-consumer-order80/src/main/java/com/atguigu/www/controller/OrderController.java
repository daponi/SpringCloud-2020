package com.atguigu.www.controller;

import com.atguigu.www.entities.CommonResult;
import com.atguigu.www.entities.Payment;
import com.atguigu.www.myLoadBalance.MyLoadBalanceImpl;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
public class OrderController {
//    public static final String PAYMENT_URL="http://localhost:8001";
    // 使用服务地址而不写死, 通过在eureka上注册过的微服务名称调用
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    //@Resources默认先按类型后按名称取spring管理的对象，MyLoadBalanceImpl面有个@Component注解就是把MyLoadBalance交给Spring管理了
    @Resource
    private MyLoadBalanceImpl myLoadBalance;

    @Resource
    private DiscoveryClient discoveryClient;

    //客户端用浏览器是get请求，但是底层实质发送post调用服务端8001
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
    }


    /**
     * 使用RestTemplate.postForEntity()
     * @param payment
     * @return
     */
    @GetMapping("/consumer/payment/createEntity")
    public CommonResult<Payment> createEntity(Payment payment){
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        return entity.getBody();
    }

    /**
     * RestTemplate.getForEntity()
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResult<Payment> getPaymentEntity(@PathVariable("id")Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }

    //手写负载均衡的轮询计算
    @GetMapping("/consumer/payment/loadBalance")
    public String getPaymentByMyLoadBalance(){
        //实例总数，即模拟的服务器总数
        List<ServiceInstance> allInstance = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (allInstance ==null || allInstance.size()<=0){
            return "No available alive servers after 10 tries from load balancer !";
        }

        //算出负载均衡的服务器实例
        ServiceInstance serverInstance = myLoadBalance.getInstances(allInstance);
        URI serverUri = serverInstance.getUri();
        return restTemplate.getForObject(serverUri+"/payment/lb", String.class);
    }

}
