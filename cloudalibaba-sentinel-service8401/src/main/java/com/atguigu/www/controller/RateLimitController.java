package com.atguigu.www.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.www.entities.CommonResult;
import com.atguigu.www.entities.Payment;
import com.atguigu.www.handler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RateLimitController {
    @GetMapping("/byResource")
    @SentinelResource(value = "myByResource",blockHandler = "handleException")
    public CommonResult byResource(){
      log.info("==============成功访问/byResource");
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }

    public CommonResult handleException(BlockException exception)
    {
        log.info("==============成功访问/handleException");
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t ,服务不可用");
    }


    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "myByUrl")
    public CommonResult byUrl()
    {
        log.info("==============成功访问byUrl");
        return new CommonResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "myCustomerBlockHandler",
              blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handleException2")
    public CommonResult customerBlockHandler()
    {
        log.info("===============成功访问/rateLimit/customerBlockHandler");
        return new CommonResult(200,"按客户自定义限流处理逻辑");
    }
}
