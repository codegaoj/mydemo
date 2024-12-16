package com.example.demo.interceptor;

import com.example.demo.config.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在这里实现权限校验逻辑
        // 例如检查请求头中的认证信息或Session中的用户信息

        // 如果权限校验失败，返回false阻止请求继续
        if (!isAuthenticated(request)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return false;
        }

        // 如果权限校验成功，返回true允许请求继续
        return true;
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        // 这里是示例逻辑，实际应用中应替换为具体的权限校验代码
        String authHeader = request.getHeader("Authorization");
        String param = request.getParameter("param");
        if (authHeader!=null){
            Object value = redisService.getValue(authHeader);
            return null != value && value.equals(param);
        }
        return false;
    }
}
