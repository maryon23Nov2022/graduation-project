package com.zhuqi.service.user.Implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhuqi.controller.Result;
import com.zhuqi.mapper.FileMapper;
import com.zhuqi.mapper.UserMapper;
import com.zhuqi.pojo.FileIndex;
import com.zhuqi.pojo.User;
import com.zhuqi.service.user.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ListServiceImpl implements ListService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public Result getList(String authorName, String modelName){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("id", "username");
        if(authorName.length() > 0) userQueryWrapper.like("username", authorName);
        List<User> authors = userMapper.selectList(userQueryWrapper);
        Map<Integer, String> authorsName = new HashMap<>();
        List<Integer> authorsId = new ArrayList<>();
        for (User user : authors) {
            authorsName.put(user.getId(), user.getUsername());
            authorsId.add(user.getId());
            System.out.printf("%s: %d\n", "ListServiceImpl", user.getId());
        }

        QueryWrapper<FileIndex> fileIndexQueryWrapper = new QueryWrapper<>();
        List<FileIndex> fileIndices = fileMapper.selectList(fileIndexQueryWrapper);
        System.out.printf("%s: %s\n", "ListServiceImpl", fileIndices);
        if(authorName.length() > 0) fileIndexQueryWrapper.in("author_id", authorsId);
        fileIndexQueryWrapper.like("modelName", modelName);
        Map<String, Object> datum = new HashMap<>();
        List<Map<String, Object>> data = new ArrayList<>();
        for(FileIndex fileIndex: fileIndices){
            datum.put("modelName", fileIndex.getModelName());
            datum.put("size", fileIndex.getSize());
            datum.put("authorName", authorsName.get(fileIndex.getAuthorId()));
            datum.put("liked", true);
            datum.put("downloadLink", "This is a link for download.");
            datum.put("imageLink", "This is a link for download image.");
            data.add(datum);
        }
        System.out.printf("%s: %s\n", "ListServiceImpl", data);
        return null;
    }

    @Override
    public Result getLiked(String authorName){
        return null;
    }
}
