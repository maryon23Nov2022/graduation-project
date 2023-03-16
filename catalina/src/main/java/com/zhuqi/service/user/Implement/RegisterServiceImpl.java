package com.zhuqi.service.user.Implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhuqi.controller.Code;
import com.zhuqi.controller.Result;
import com.zhuqi.mapper.UserMapper;
import com.zhuqi.pojo.User;
import com.zhuqi.service.user.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result addUser(HttpSession httpSession, User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        Boolean flag = null == userMapper.selectOne(queryWrapper);
        String msg;
        if(flag){
            if(user.getId() == null) System.out.printf("%s: %s\n", "RegisterServiceImpl", "id is empty");
            userMapper.insert(user);
            System.out.printf("%s: %d\n", "RegisterServiceImpl", user.getId());
            httpSession.setAttribute("id", user.getId());
            httpSession.setAttribute("username", user.getUsername());
            msg = "user added";
        } else msg = "username already exist.";
        return new Result(flag ? Code.OK : Code.ERR, user.getUsername(), msg);
    }
}
