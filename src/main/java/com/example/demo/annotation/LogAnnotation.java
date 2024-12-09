package com.example.demo.annotation;

import com.example.demo.mode.myenum.EventType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogAnnotation {

    EventType eventType() default EventType.BUSINESS;

    String businessName() default "业务名称";

    String userName() default "操作人名称";
}
