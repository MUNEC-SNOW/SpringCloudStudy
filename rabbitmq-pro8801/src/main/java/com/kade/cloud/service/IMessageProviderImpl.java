package com.kade.cloud.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;


@EnableBinding(Source.class)
public class IMessageProviderImpl implements IMessageProvider{
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();

        Message<String> message = MessageBuilder.withPayload(serial).build();
        output.send(message);
        System.out.print("*************serial: " + serial);
        return null;
    }
}
