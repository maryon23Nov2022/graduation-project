package com.zhuqi.controller;

import com.zhuqi.service.user.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController{
    @Autowired
    private FileService fileService;

    @PostMapping
    public Result receiveFile(HttpSession httpSession, MultipartFile modelChosen, MultipartFile imgChosen, String modelName){
        return fileService.receiveFile(httpSession, modelChosen, imgChosen, modelName);
    }

    @GetMapping(produces = {"application/octet-stream;charset=utf-8"})
    public byte[] sendFile(Integer fileId, HttpServletResponse httpServletResponse) throws IOException {
        return fileService.sendFile(fileId, httpServletResponse);
    }
}
