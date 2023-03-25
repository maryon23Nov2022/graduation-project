package com.zhuqi.service.user.Implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhuqi.controller.Code;
import com.zhuqi.controller.Result;
import com.zhuqi.mapper.FileMapper;
import com.zhuqi.pojo.FileIndex;
import com.zhuqi.service.user.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private FileMapper fileMapper;

    @Override
    public Result receiveFile(HttpSession httpSession, MultipartFile modelChosen, MultipartFile imgChosen, String modelName){
        Integer authorId = (Integer)httpSession.getAttribute("id");
        if(authorId == null) System.out.printf("%s: %s\n", "FileServiceImpl", "authorId is null");
        else System.out.printf("%s: %d\n", "FileServiceImpl", authorId);
        Long modelSize = modelChosen.getSize();
        String path;
        if("\\".equals(File.separator))
            path = "C:\\Users\\zhuqi\\Desktop\\2022-2023-2\\graduation-project\\models" + File.separator + authorId + File.separator + modelName;
        else path = "/home/zhuqi/models" + File.separator + authorId + File.separator + modelName;
        File file = new File(path);
        if(!file.exists()) file.mkdirs();
        else return new Result(Code.ERR, null, "该模型名已被您使用过");
        File modelFile = new File(path + File.separator + modelName + ".glb");
        File imgFile = new File(path + File.separator + modelName + ".jpg");
        System.out.printf("%s: %s %s\n", "FileServiceImpl", modelChosen.getOriginalFilename(), imgChosen.getOriginalFilename());
        System.out.printf("%s: %s\n", "FileServiceImpl", modelFile.getPath());
        try(
            FileOutputStream modelFileOutputStream = new FileOutputStream(modelFile.getPath());
            FileOutputStream imgFileOutputStream = new FileOutputStream(imgFile.getPath());
            BufferedOutputStream modelBufferedOutputStream = new BufferedOutputStream(modelFileOutputStream);
            BufferedOutputStream imgBufferedOutputStream = new BufferedOutputStream(imgFileOutputStream);
        ){
            modelBufferedOutputStream.write(modelChosen.getBytes());
            imgBufferedOutputStream.write(imgChosen.getBytes());
            fileMapper.insert(new FileIndex(null, modelName, authorId, modelSize, new Date()));
            return new Result(Code.OK, null, "模型添加成功");
        } catch(IOException e){
            return new Result(Code.OK, null, "模型添加失败");
        }
    }

    @Override
    public byte[] sendFile(Integer fileId, HttpServletResponse httpServletResponse) throws IOException{
        System.out.printf("%s: %d\n", "sendFileImpl", fileId);
        QueryWrapper<FileIndex> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", fileId);
        FileIndex fileIndex = fileMapper.selectOne(queryWrapper);
        String path = "C:\\Users\\zhuqi\\Desktop\\2022-2023-2\\graduation-project\\models" + File.separator + fileIndex.getAuthorId() + File.separator + fileIndex.getModelName();
        System.out.printf("%s: %s\n", "sendFileImpl", fileIndex.getModelName());
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + fileIndex.getModelName() + ".glb");
        InputStream inputStream = new FileInputStream(path + File.separator + fileIndex.getModelName() + ".glb");
        return IOUtils.toByteArray(inputStream);
    }
}