package com.zhuqi.controller;

import com.zhuqi.service.user.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListController{
    @Autowired
    private ListService listService;

    @GetMapping("/list")
    public Result getList(String authorName, String modelName){
        return listService.getList(authorName, modelName);
    }

    @GetMapping("/bookmarks")
    public Result getLiked(String authorName){
        return listService.getLiked(authorName);
    }
}
