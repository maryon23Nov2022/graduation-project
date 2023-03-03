package com.zhuqi.controller;

import com.alibaba.fastjson2.JSON;
import com.zhuqi.pojo.User;
import com.zhuqi.service.user.DeleteService;
import com.zhuqi.service.user.GetInfoService;
import com.zhuqi.service.user.RegisterService;
import com.zhuqi.service.user.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//Use annotation to define bean
@RestController
@RequestMapping(value = {"/users"})
//@RequestMapping(value = {"/users"}, produces = {"application/json;charset=utf-8"})
public class UserController{
    @Autowired
    private RegisterService registerService;
    @Autowired
    private GetInfoService getInfoService;
    @Autowired
    private UpdateService updateService;
    @Autowired
    private DeleteService deleteService;


    @GetMapping
    public Result getUser(String req){
        User user = JSON.parseObject(req, User.class);
//        return getInfoService.getInfo(user);
        return null;
    }

    @PostMapping
    public Result addUser(@RequestBody User user){
        return registerService.addUser(user);
    }

    @PutMapping
    public Result updateUser(@RequestBody User user){
        return updateService.updateUser(user);
    }

    @DeleteMapping
    public Result deleteUser(@RequestBody User user){
        return deleteService.deleteUser(user);
    }
}