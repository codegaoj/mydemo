package com.example.demo.aspect;

import com.example.demo.config.Publisher;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    @Autowired
    private Publisher publisher;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("@annotation(com.example.demo.annotation.LogAnnotation)")
    public void loggableMethods() {}

    // 环绕通知
    @Around("loggableMethods()")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            publisher.send(redisTemplate);
            System.out.println("Executing method: " + joinPoint.getSignature());//1
            return joinPoint.proceed();//3
        } finally {
            long elapsedTime = System.currentTimeMillis() - start;
            System.out.println("Method executed in " + elapsedTime + "ms");//4
        }
    }

    // 前置通知
    @Before("loggableMethods()")
    public void logBefore() {
        System.out.println("Logging before method execution");//2
    }

    // 后置返回通知
    @AfterReturning(value = "loggableMethods()",returning = "result")
    public void logAfterReturning(Object result) {
        System.out.println("Method returned value: " + result);//6
    }

    // 后置最终通知
    @After("loggableMethods()")
    public void logAfter() {
        System.out.println("Logging after method execution, regardless of outcome");//5
    }

    // 后置异常通知
    @AfterThrowing(value = "loggableMethods()",throwing = "ex")
    public void logAfterThrowing(Throwable ex) {
        System.out.println("Exception thrown: " + ex);
    }


    //有异常的情况
//    Executing method: void com.example.demo.service.impl.AopServiceImpl.processRequest(String)
//    Logging before method execution
//    Method executed in 2ms
//    Logging after method execution, regardless of outcome
//    Exception thrown: java.lang.ArithmeticException: / by zero


    //无异常的情况
//    Executing method: void com.example.demo.service.impl.AopServiceImpl.processRequest(String)
//    Logging before method execution
//    测试aop"aaa"
//    Method executed in 3ms
//    Logging after method execution, regardless of outcome
//    Method returned value: null

}
