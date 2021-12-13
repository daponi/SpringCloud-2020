package com.atguigu.www.service.impl;

import com.atguigu.www.service.MyMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;


@Slf4j
@EnableBinding(Source.class) // 可以理解为是一个消息的发送管道的定义
public class MyMessageProviderImpl implements MyMessageProvider {
    @Resource
    private MessageChannel output; // 消息的发送管道


    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());// 创建并发送消息
        log.info("=======发送消息serial:{}",serial);
        return serial;
    }
}
