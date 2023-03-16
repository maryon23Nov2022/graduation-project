package com.zhuqi.service.user;


import com.zhuqi.controller.Result;
import com.zhuqi.pojo.User;

import javax.servlet.http.HttpSession;

public interface RegisterService {
    Result addUser(HttpSession httpSession, User user);
}
