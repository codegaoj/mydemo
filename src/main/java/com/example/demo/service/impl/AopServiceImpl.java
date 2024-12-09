package com.example.demo.service.impl;

import com.example.demo.annotation.LogAnnotation;
import com.example.demo.mode.myenum.EventType;
import com.example.demo.service.AopService;
import org.springframework.stereotype.Service;

@Service
public class AopServiceImpl implements AopService {

    @LogAnnotation(businessName = "yw",userName = "me",eventType = EventType.BUSINESS)
    public void processRequest(String param) {
        // 方法逻辑
//        int a=1/0;
        System.out.println("测试aop"+param);
    }
}
