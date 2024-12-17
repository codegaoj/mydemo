package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
@Configuration
public class Subscriber {
    private static final String CHANNEL = "myChannel";
    @Value("${spring.redis.host}")
    private static String host;

    @Autowired
    private RedisReceiver receiver;

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

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.afterPropertiesSet();
        //订阅频道，通配符*表示任意多个占位符
//        container.addMessageListener(receiver, new PatternTopic("mychanne*"));
        container.addMessageListener(receiver, new PatternTopic("mychannel"));
        return container;
    }

/*    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    Receiver receiver() {
        return new Receiver();
    }*/
}