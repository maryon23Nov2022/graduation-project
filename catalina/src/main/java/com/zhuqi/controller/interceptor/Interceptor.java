package com.zhuqi.controller.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Interceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setContentType("text/plain;charset=UTF-8");
        System.out.printf("%s\n", "preHandle executed\n");
        System.out.printf("%s: %s\n", "Interceptor", request.getRequestURL());
        return true;        //return false to intercept request and abort process
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        System.out.printf("%s: %s\n", "response is", response.toString());
    }
}
