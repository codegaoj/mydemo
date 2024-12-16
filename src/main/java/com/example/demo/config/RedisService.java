package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    //1.实现简单登录
    //2.校验权限
    //3.查询在线用户
    //4.实现消息队列
    //5.实现分布式锁
    //todo aop、拦截器（interceptor）、过滤器(filter)

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void setValue(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public void setValue(String key,String value,long time){
        redisTemplate.opsForValue().set(key,value,time, TimeUnit.MILLISECONDS);
    }

    public Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
