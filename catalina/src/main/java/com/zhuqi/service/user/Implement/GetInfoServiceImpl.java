package com.zhuqi.service.user.Implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhuqi.controller.Code;
import com.zhuqi.controller.Result;
import com.zhuqi.mapper.UserMapper;
import com.zhuqi.pojo.User;
import com.zhuqi.service.user.GetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class GetInfoServiceImpl implements GetInfoService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result getInfo(HttpSession httpSession, User user){
        System.out.printf("%s %s\n", "GetInfoServiceImpl", "queried");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        System.out.printf("%s: %s\n", "GetInfoServiceImpl", user);
        User u = userMapper.selectOne(queryWrapper);
        if(u == null){
            return new Result(Code.ERR, null, "用户名或密码错误");
        } else {
            httpSession.setAttribute("id", u.getId());
            httpSession.setAttribute("username", u.getUsername());
            return new Result(Code.OK, u, null);
        }
    }
}
