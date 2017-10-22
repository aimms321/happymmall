package com.happymmall.service.impl;

import com.happymmall.service.IFileService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件，上传文件的文件名：{}，上传的路径为{} ，新文件名：{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);

        try {
            file.transferTo(targetFile);
            //todo 将targetFile上传到FTP服务器上
            //todo 上传完之后，删除upload下面的文件
        } catch (IOException e) {
            logger.error("上传文件异常",e);
        }
        return targetFile.getName();
    }

}
