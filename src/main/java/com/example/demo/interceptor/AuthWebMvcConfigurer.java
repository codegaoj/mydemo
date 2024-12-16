package com.example.demo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class AuthWebMvcConfigurer implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    public AuthWebMvcConfigurer(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器并指定要拦截的路径和排除路径
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/**") // 拦截所有/api/下的请求
                .excludePathPatterns("/api/login/**"); // 排除/api/public/下的请求
    }
}
