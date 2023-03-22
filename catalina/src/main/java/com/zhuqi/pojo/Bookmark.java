package com.zhuqi.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("bookmark")
public class Bookmark{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer fileId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Bookmark(){}

    public Bookmark(Integer id, Integer userId, Integer fileId) {
        this.id = id;
        this.userId = userId;
        this.fileId = fileId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}
