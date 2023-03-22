package com.zhuqi.service.Implement;

import com.zhuqi.controller.Result;
import com.zhuqi.mapper.BookmarkMapper;
import com.zhuqi.pojo.Bookmark;
import com.zhuqi.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{
    @Autowired
    private BookmarkMapper bookmarkMapper;
    @Override
    public Result newBookmark(Integer userId, Integer fileId){
        Bookmark bookmark = new Bookmark(null, userId, fileId);
        bookmarkMapper.insert(bookmark);
        return null;
    }
    @Override
    public Result deleteBookmark(Integer userId, Integer fileId){
        return null;
    }
}
