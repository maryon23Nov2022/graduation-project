package com.zhuqi.service;

import com.zhuqi.controller.Result;

public interface LikeService{
    Result newBookmark(Integer userId, Integer fileId);
    Result deleteBookmark(Integer userId, Integer fileId);
}
