package com.example.demo.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomGatewayFilterFactory.Config> {

    public CustomGatewayFilterFactory(){
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // 使用config中的参数执行自定义逻辑
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header("Custom-Header", config.getHeaderValue())
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    public static class Config{
        private String headerValue;

        // Getters and setters for the configuration parameters
        public String getHeaderValue() {
            return headerValue;
        }

        public void setHeaderValue(String headerValue) {
            this.headerValue = headerValue;
        }
    }
}
