package com.zhuqi.controller;

import com.zhuqi.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/collect")
public class LikeController{
    @Autowired
    private LikeService likeService;

    @PostMapping
    public Result newBookmark(HttpSession httpSession, Integer fileId){
        return likeService.newBookmark((Integer)httpSession.getAttribute("id"), fileId);
    }

    @DeleteMapping("/{fileId}")
    public Result deleteBookmark(HttpSession httpSession, @PathVariable Integer fileId){
        System.out.printf("%s: %d\n", "LikeController", fileId);
        return likeService.deleteBookmark((Integer)httpSession.getAttribute("id"), fileId);
    }
}
