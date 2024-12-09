package com.example.demo.service.impl;

import com.example.demo.service.AopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopServiceImplTest {

    @Autowired
    private AopService aopService;


    @Test
    public void processRequest() {
        aopService.processRequest("test-aop");
    }
}