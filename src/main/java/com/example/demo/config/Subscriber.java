package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class Subscriber {
    private static final String CHANNEL = "myChannel";
    @Value("${spring.redis.host}")
    private static String host;
    public static void main(String[] args) {
        try (Jedis jedis = new Jedis("192.168.91.99")) {
            jedis.subscribe(new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    System.out.println("Received on channel '" + channel + "': " + message);
                }
            }, CHANNEL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}