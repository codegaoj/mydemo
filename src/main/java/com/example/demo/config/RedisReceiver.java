package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class RedisReceiver implements MessageListener {

    @Autowired
    private RedisService redisService;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        if(redisService.tryAcquireLock()){
            try {
                System.out.println("--------接收消息"+ new String(message.getChannel()) +"-----------");
                System.out.println(new String(message.getBody()));
                System.out.println("--------结束接收消息"+ new String(message.getChannel()) +"-----------");
            } finally {
                redisService.releaseLock();
            }

        }else {
            System.out.println("已经有消费者在处理。。。");
        }
    }


}
