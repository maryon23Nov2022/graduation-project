package com.zhuqi.config;

import com.zhuqi.controller.interceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Create Config file and load corresponding bean
@Configuration
@ComponentScan(value = {"com.zhuqi"})
@EnableWebMvc   //Should be commented out when running test program, not sure for reason yet
@PropertySource(value = {"classpath:jdbc.properties"})  //specify property source used for class preceded by @Import
@Import(value = {JdbcConfig.class, MybatisPlusConfig.class, MultipartResolverConfig.class})
public class SpringMvcConfig implements WebMvcConfigurer{
    @Autowired
    private Interceptor interceptor; //@EnableWebMvc is required.

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        System.out.printf("interceptor added\n");
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}