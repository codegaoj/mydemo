package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

//    默认情况下，Spring Boot会自动配置一个StringRedisTemplate，它是一个使用字符串作为键和值的RedisTemplate版本。
//    如果你需要自定义RedisTemplate，比如指定不同的序列化方式或者支持更复杂的数据类型，你需要自己定义并注册这个bean。
//    可以通过创建一个配置类来实现这一点

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 设置键（key）的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        // 设置值（value）的序列化方式
        // 如果需要自定义序列化方式，可以在这里进行配置
        // 例如：template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return template;
    }
}
