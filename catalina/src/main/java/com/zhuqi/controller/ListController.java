package com.zhuqi.controller;

import com.zhuqi.service.user.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/list")
@RestController
public class ListController{
    @Autowired
    private ListService listService;

    @GetMapping
    public Result getList(HttpSession httpSession, String authorName, String modelName, Boolean liked){
        return listService.getList((Integer)httpSession.getAttribute("id"), authorName, modelName, liked);
    }
}
