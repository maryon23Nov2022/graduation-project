package com.zhuqi.service.user.Implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhuqi.controller.Code;
import com.zhuqi.controller.Result;
import com.zhuqi.mapper.BookmarkMapper;
import com.zhuqi.mapper.FileMapper;
import com.zhuqi.mapper.UserMapper;
import com.zhuqi.pojo.Bookmark;
import com.zhuqi.pojo.FileIndex;
import com.zhuqi.pojo.User;
import com.zhuqi.service.user.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ListServiceImpl implements ListService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private BookmarkMapper bookmarkMapper;

    public Set<Integer> getBookmarkSet(Integer userId){
        QueryWrapper<Bookmark> bookmarkQueryWrapper = new QueryWrapper<>();
        bookmarkQueryWrapper.eq("user_id", userId);
        List<Bookmark> bookmarkList =  bookmarkMapper.selectList(bookmarkQueryWrapper);
        Set<Integer> bookmarkSet = new HashSet<>();
        for(Bookmark bookmark : bookmarkList){
            bookmarkSet.add(bookmark.getFileId());
        }
        System.out.printf("%s: %s\n", "ListServiceImpl", bookmarkSet);
        return bookmarkSet;
    }

    @Override
    public Result getList(Integer userId, String authorName, String modelName, Boolean liked){
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

        Set<Integer> bookmarkSet = getBookmarkSet(userId);

        QueryWrapper<FileIndex> fileIndexQueryWrapper = new QueryWrapper<>();
        if(authorName.length() > 0) fileIndexQueryWrapper.in("author_id", authorsId);
        if(modelName.length() > 0) fileIndexQueryWrapper.like("model_name", modelName);
        if(liked && bookmarkSet.size() > 0) fileIndexQueryWrapper.in("id", bookmarkSet);
        List<FileIndex> fileIndices = fileMapper.selectList(fileIndexQueryWrapper);
        System.out.printf("%s: %s\n", "ListServiceImpl", fileIndices);
        List<Map<String, Object>> data = new ArrayList<>();     //The List is an ordered collection that contains object references.
        for(FileIndex fileIndex: fileIndices){
            Map<String, Object> datum = new HashMap<>();
            datum.put("fileId", fileIndex.getId());
            datum.put("modelName", fileIndex.getModelName());
            System.out.printf("%s: %s\n", "ListServiceImpl", fileIndex.getModelName());
            datum.put("size", fileIndex.getSize());
            datum.put("authorName", authorsName.get(fileIndex.getAuthorId()));
            datum.put("liked", bookmarkSet.contains(fileIndex.getId()));
            String basePath = "http://127.0.0.1:3016/" + fileIndex.getAuthorId() + "/" + fileIndex.getModelName() + "/";
            datum.put("downloadLink", basePath + fileIndex.getModelName() + ".glb");
            datum.put("imageLink", basePath + fileIndex.getModelName() + ".jpg");
            data.add(datum);
        }
        System.out.printf("%s: %s\n", "ListServiceImpl", data);
        return new Result(Code.OK, data, null);
    }

    @Override
    public Result getLiked(Integer userId){
        return null;
    }
}
