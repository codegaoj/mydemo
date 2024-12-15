package com.example.demo.service.impl;

import com.example.demo.config.RedisService;
import com.example.demo.service.AopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopServiceImplTest {

    @Autowired
    private AopService aopService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void processRequest() {
        aopService.processRequest("test-aop");
    }


    @Test
    public void redisTest() {
        stringRedisTemplate.opsForValue().set("test1","高二年级");
        Object test = stringRedisTemplate.opsForValue().get("test1");
        System.out.println(test);
    }

    @Test
    public void myredisTest() {
        redisService.setValue("test2","高二年级");
        Object test = redisService.getValue("test2");
        System.out.println(test);
    }
}