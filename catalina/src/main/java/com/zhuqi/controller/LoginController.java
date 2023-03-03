package com.zhuqi.controller;

import com.zhuqi.pojo.User;
import com.zhuqi.service.GetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController{
    @Autowired
    private GetInfoService getInfoService;

    @PostMapping
    public Result getInfo(User user){
        return getInfoService.getInfo(user);
    }
}
