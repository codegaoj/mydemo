package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void setValue(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
