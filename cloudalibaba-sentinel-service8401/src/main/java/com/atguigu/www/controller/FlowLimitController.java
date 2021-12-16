package com.atguigu.www.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() throws InterruptedException {
        log.info("==============成功访问/testA");
//        Thread.sleep(2000);
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() throws InterruptedException {
        long begin=System.currentTimeMillis();
        int sleepTime=1100;

        Thread.sleep(sleepTime);
        long end=System.currentTimeMillis();
        log.info("==============成功访问/testB,线程ID：{}，\t 处理任务耗时:{}",Thread.currentThread().getName(),end-begin);
        return "------testB";
    }

    @GetMapping("/testC")
    public String testC() throws InterruptedException {
        log.info("==============成功访问/testC");
        int i=10/0;
//        Thread.sleep(2000);
        return "------testC";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "myTestHotkey",blockHandler = "dealHandler_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        int i=10/0;
        log.info("=============成功访问：/testHotKey");
        return "------testHotKey";
    }
    public String dealHandler_testHotKey(String p1, String p2, BlockException exception)
    {
        log.info("=============成功访问：dealHandler_testHotKey");
        return "-----dealHandler_testHotKey";
    }

}
