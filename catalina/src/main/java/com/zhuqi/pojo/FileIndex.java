package com.zhuqi.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("file_index")
public class FileIndex {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String modelName;
    private Integer authorId;
    private Long size;
    private Date date;

    public FileIndex() {}

    public FileIndex(Integer id, String filename, Integer authorId, Long size, Date date){
        this.id = id;
        this.modelName = filename;
        this.authorId = authorId;
        this.size = size;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}