package com.example.springboot_vue.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandler implements HandlerInterceptor {

//    @Autowired
//    RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        String path = request.getServletPath();

        // 登录，注册以及注册时获取验证码的接口需要放开
        if (uri.contains("login") || uri.contains("register") || uri.contains("getCode") || uri.contains("allSearch")) {
            return true;
        }

        if (path.contains("/login") || path.contains("/register") || path.contains("/getCode") || path.contains("allSearch")) {
            return true;
        } else {
            String token = request.getHeader("token");
            System.out.println("在拦截器中获取到token = " + token);
//            String value = (String) redisTemplate.opsForValue().get(token);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
