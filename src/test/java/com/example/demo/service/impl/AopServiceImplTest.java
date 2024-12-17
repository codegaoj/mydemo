package com.example.demo.service.impl;

import com.example.demo.config.ConsumerRunner;
import com.example.demo.config.Publisher;
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

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopServiceImplTest {

    @Autowired
    private AopService aopService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Publisher publisher;

    @Autowired
    private ConsumerRunner consumerRunner;

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

    @Test
    public void myredisMqTest() {
        publisher.send(stringRedisTemplate);
//        for (int i = 0; i < 5; i++) {
//            boolean b = redisService.tryAcquireLock();
//            try {
//                Thread.sleep(7000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(b);
//        }
//        redisService.tryAcquireLock();
        System.out.println("--end---");
    }


    @Test
    public void myredisZsetTest() {
        System.out.println("--start---");
        for (int i = 0; i < 5; i++) {
            long l = System.currentTimeMillis();
            redisService.setZsetValue("mySortSet",String.valueOf(i),l);
        }
        Set<Object> mySortSet = redisService.paginate("mySortSet", 1, 3);
        System.out.println(mySortSet);
        System.out.println("--end---");
    }
}