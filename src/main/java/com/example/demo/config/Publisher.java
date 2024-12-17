package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
@Component
public class Publisher {

    private static final String CHANNEL = "myChannel";
    @Value("${spring.redis.host}")
    private static String host;

    @Autowired
    private StringRedisTemplate redisTemplate;

/*    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("192.168.91.99")) {
            for (int i = 0; i < 10; i++) {
                String message = "PubSubMessage-" + i;
                jedis.publish(CHANNEL, message);
                System.out.println("Published: " + message);
                Thread.sleep(1000); // 模拟发布延迟
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void send(StringRedisTemplate redisTemplate) {
        for (int i = 0; i < 10; i++) {
            redisTemplate.convertAndSend("mychannel","test"+i);
            try {
                Thread.sleep(2000); // 模拟发布延迟
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
