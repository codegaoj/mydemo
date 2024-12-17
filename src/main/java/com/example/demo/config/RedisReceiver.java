package com.example.demo.config;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisReceiver implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("--------接收消息"+ new String(message.getChannel()) +"-----------");
        System.out.println(new String(message.getBody()));
    }
}
