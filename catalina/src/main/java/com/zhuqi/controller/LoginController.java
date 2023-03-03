package com.zhuqi.controller;

import com.zhuqi.pojo.User;
import com.zhuqi.service.user.GetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController{
    @Autowired
    private GetInfoService getInfoService;

    @PostMapping
    public Result getInfo(HttpSession httpSession, User user){
        return getInfoService.getInfo(httpSession, user);
    }

    @GetMapping
    public Result getUsername(HttpSession httpSession){
        Object username = httpSession.getAttribute("username");
        System.out.printf("LoginController: %s\n", httpSession.getId());
        if(username == null){
            return new Result(Code.ERR, null, "unlisted");
        } else{
            System.out.printf("LoginController: %s\n", username);
            return new Result(Code.OK, username, "logged");
        }
    }

    @DeleteMapping
    public Result signOut(HttpSession httpSession){
        httpSession.removeAttribute("username");
        httpSession.removeAttribute("id");
        return new Result(Code.OK, null, null);
    }
}
