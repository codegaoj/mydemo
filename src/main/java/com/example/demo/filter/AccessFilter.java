package com.example.demo.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class AccessFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 在这里添加自定义逻辑，例如修改请求或响应
        ServerHttpRequest request = exchange.getRequest().mutate()
                .header("Custom-Header", "HeaderValue") // 添加自定义头部
                .build();
        return chain.filter(exchange.mutate().request(request).build());
    }

    //todo
    //接入网关逻辑和sdk
    //使用mq模拟附件穿透逻辑
    //高并发场景，多线程
}
