package com.example.demo.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ConsumerRunner implements CommandLineRunner {

    @Autowired
    private RedisReceiver redisReceiver;

    public void startConsuming() {
        // 这里可以添加一些逻辑，例如初始化连接等
        System.out.println("Consumer started.");
    }
    @Override
    public void run(String... args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5); // 创建一个固定大小为5的线程池
        for (int i = 0; i < 5; i++) {
            executor.submit(this::startConsuming);
        }
        executor.shutdown();
    }
}
