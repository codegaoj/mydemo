package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.example.demo.Constant.Constant.LOCK_KEY;

@Component
public class RedisService {

    //1.实现简单登录
    //2.校验权限
    //3.查询在线用户
    //4.实现消息队列
    //5.实现分布式锁
    //aop、拦截器（interceptor）、过滤器(filter)

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

    public boolean tryAcquireLock() {
        return redisTemplate.opsForValue().setIfAbsent(LOCK_KEY, "LOCK", Duration.ofSeconds(10));
    }


    public void releaseLock() {
        redisTemplate.delete(LOCK_KEY);
    }

    public void setZsetValue(String key,String value,double score){
        redisTemplate.opsForZSet().add(key,value,score);
    }

    /**
     * 分页查询 ZSet 中的数据。
     *
     * @param key      ZSet 的键
     * @param page     当前页码（从1开始）
     * @param pageSize 每页显示的项目数
     * @return 返回指定页码和页面大小的 ZSet 成员集合
     */
    public Set<Object> paginate(String key, int page, int pageSize) {
        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();

        // 计算起始索引和结束索引
        long start = (page - 1) * pageSize;
        long end = start + pageSize - 1;

        // 使用 zrange 获取按分数升序排列的结果
//        return zSetOps.range(key, start, end);

        // 使用 zrevrange 获取按分数降序排列的结果
        return zSetOps.reverseRange(key, start, end);

        // 获取成员及其分数，按分数升序
//        Set<ZSetOperations.TypedTuple<Object>> entries = zSetOps.rangeWithScores(key, start, end);

        // 获取成员及其分数，按分数降序
//        Set<ZSetOperations.TypedTuple<Object>> entries = zSetOps.reverseRangeWithScores(key, start, end);
//        return Collections.singleton(entries);
    }

}
