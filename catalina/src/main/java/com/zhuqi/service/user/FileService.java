package com.zhuqi.service.user;

import com.zhuqi.controller.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService{
    Result receiveFile(HttpSession httpSession, MultipartFile fileChosen, MultipartFile imgChosen, String modelName);

    byte[] sendFile(Integer fileId, HttpServletResponse httpServletResponse) throws IOException;
}
