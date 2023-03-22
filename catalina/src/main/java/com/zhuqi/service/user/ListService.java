package com.zhuqi.service.user;

import com.zhuqi.controller.Result;

public interface ListService{
    Result getList(String authorName, String modelName);

    Result getLiked(String authorName);
}
