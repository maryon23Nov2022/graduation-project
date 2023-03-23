package com.zhuqi.controller;

import com.zhuqi.service.user.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class ListController{
    @Autowired
    private ListService listService;

    @GetMapping("/list")
    public Result getList(HttpSession httpSession, String authorName, String modelName){
        return listService.getList((Integer)httpSession.getAttribute("id"), authorName, modelName, false);
    }

    @GetMapping("/bookmarks")
    public Result getLiked(HttpSession httpSession, String authorName){
        return listService.getList((Integer)httpSession.getAttribute("id"), "", "", true);
    }
}
