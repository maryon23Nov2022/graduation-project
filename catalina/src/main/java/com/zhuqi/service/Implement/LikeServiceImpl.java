package com.zhuqi.service.Implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhuqi.controller.Code;
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
        return new Result(Code.OK, null, "Liked.");
    }
    @Override
    public Result deleteBookmark(Integer userId, Integer fileId){
        QueryWrapper<Bookmark> bookmarkQueryWrapper = new QueryWrapper<>();
        bookmarkQueryWrapper.eq("user_id", userId);
        bookmarkQueryWrapper.eq("file_id", fileId);
        System.out.printf("%s: %d, %d\n", "deleteBookmark", userId, fileId);
        bookmarkMapper.delete(bookmarkQueryWrapper);
        return new Result(Code.OK, null, "Disliked");
    }
}
