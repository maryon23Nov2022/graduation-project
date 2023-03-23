package com.zhuqi.service.user;

import com.zhuqi.controller.Result;

public interface ListService{
    Result getList(Integer userId, String authorName, String modelName, Boolean liked);

    Result getLiked(Integer userId);
}
