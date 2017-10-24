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
        //将上传上来的文件名更改为随机的
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf("."));
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        //LOGGER记录上传的文件信息
        logger.info("开始上传文件，上传文件的文件名：{}，上传的路径为{} ，新文件名：{}",fileName,path,uploadFileName);
        //创建WEBINFO下的临时上传文件夹
        File fileDir = new File("upload");
        //判断文件夹是否存在
        if (!fileDir.exists()) {
            fileDir.setWritable(true); //将文件夹设置为可写
            fileDir.mkdirs();//创建文件夹
        }
        //创建更名后的文件
        File targetFile = new File("upload", uploadFileName);
        //将上传的文件数据复制到新创建的更名文件中
        try {
            file.transferTo(targetFile);
            //todo 将文件上传到FTP服务器上
            //todo 将文件删除
        } catch (IOException e) {
            logger.error("上传文件异常", e);
        }
        return targetFile.getName();


//        String fileName = file.getOriginalFilename();
//        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
//        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
//        logger.info("开始上传文件，上传文件的文件名：{}，上传的路径为{} ，新文件名：{}",fileName,path,uploadFileName);
//
//        File fileDir = new File(path);
//        if (!fileDir.exists()) {
//            fileDir.setWritable(true);
//            fileDir.mkdirs();
//        }
//        File targetFile = new File(path, uploadFileName);
//
//        try {
//            file.transferTo(targetFile);
//            //todo 将targetFile上传到FTP服务器上
//            //todo 上传完之后，删除upload下面的文件
//        } catch (IOException e) {
//            logger.error("上传文件异常",e);
//        }
//        return targetFile.getName();
    }

}
