package com.zhuqi.controller;

import com.zhuqi.pojo.User;
import com.zhuqi.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegisterController{
    @Autowired
    private RegisterService registerService;

    @PostMapping
    public Result addUser(User user){
        return registerService.addUser(user);
    }
}
