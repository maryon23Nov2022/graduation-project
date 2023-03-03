package com.zhuqi.service.user;

import com.zhuqi.controller.Result;
import com.zhuqi.pojo.User;

import javax.servlet.http.HttpSession;

public interface GetInfoService{
    Result getInfo(HttpSession httpSession, User user);
}
