package com.zhuqi.controller;

import com.zhuqi.pojo.User;
import com.zhuqi.service.user.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/register")
public class RegisterController{
    @Autowired
    private RegisterService registerService;

    @PostMapping
    public Result addUser(HttpSession httpSession, User user){
        return registerService.addUser(httpSession, user);
    }
}
