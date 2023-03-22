package com.zhuqi.controller;

import com.zhuqi.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/bookmarks")
public class LikeController{
    @Autowired
    private LikeService likeService;

    @GetMapping
    public Result newBookmark(HttpSession httpSession, Integer fileId){
        return likeService.newBookmark((Integer)httpSession.getAttribute("id"), fileId);
    }

    @DeleteMapping
    public Result deleteBookmark(HttpSession httpSession, Integer fileId){
        return likeService.deleteBookmark((Integer)httpSession.getAttribute("id"), fileId);
    }
}
