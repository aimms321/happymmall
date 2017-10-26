package com.happymmall.util;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.ftp.FtpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FTPUtil {


    private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    private static String ftpIP = PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtil.getProperty("ftp.pass");
    private static int ftpPort = Integer.parseInt(PropertiesUtil.getProperty("ftp.port"));


//创建供外部调用的静态方法,返回是否成功
    public static boolean uploadFile(List<File> fileList) throws IOException {
        //使用静态变量给构造方法传值创建对象
        FTPUtil ftpUtil = new FTPUtil(ftpIP, ftpPort, ftpUser, ftpPass);
        logger.info("开始连接FTP服务器");
        boolean result = ftpUtil.uploadFile("img", fileList);
        logger.info("FTP服务器断开，上传结束，上传结果为{}", result);
        return result;
    }

    private boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
        boolean uploaded = true;//默认值是true
        FileInputStream fis = null;
        if (connectServer(this.ip, this.port, this.user, this.pwd)) {
            try {

                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for (File fileItem : fileList) {
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(), fis);
                }
            } catch (IOException e) {
                logger.error("上传文件异常", e);
                uploaded=false;
            } finally {
                fis.close();
                ftpClient.disconnect();
            }

        }
        return uploaded;
    }


    private boolean connectServer(String ip, int port, String user, String pwd) {
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.setDefaultPort(port);
            ftpClient.connect(ip);
            isSuccess=ftpClient.login(user, pwd);
        } catch (IOException e) {
            logger.error("连接FTP服务器异常",e);
        }
        return isSuccess;
    }

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
//    public FTPUtil(String ip, int port, String user, String pwd) {
//        this.ip = ip;
//        this.port = port;
//        this.user = user;
//        this.pwd = pwd;
//    }
//
//    public static boolean uploadFile(List<File> fileList) {
//        FTPUtil ftpUtil = new FTPUtil(ftpIP, 21, ftpUser, ftpPass);
//        logger.info("开始连接FTP服务器");
//    }
//
//    private boolean uploadFile(String remotePath, List<File> fileList) {
//        boolean uploaded = true;
//        FileInputStream fis = null;
//        if (connectServer(this.ip, this.port, this.user, this.pwd)) {
//            try {
//                ftpClient.changeWorkingDirectory(remotePath);
//                ftpClient.setBufferSize(1024);
//                ftpClient.setControlEncoding("UTF-8");
//                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//                ftpClient.enterLocalPassiveMode();
//                for (File fileItem : fileList) {
//                    fis = new FileInputStream(fileItem);
//                    ftpClient.storeFile(fileItem.getName(), fis);
//                }
//            } catch (IOException e) {
//                logger.error("上传文件异常",e);
//            }
//        }
//
//    }
//
//    private boolean connectServer(String ip, int port, String user, String pwd) {
//        boolean isSuccess = false;
//        ftpClient = new FTPClient();
//        try {
//            ftpClient.connect(ip);
//            ftpClient.login(user, pwd);
//        } catch (IOException e) {
//            logger.error("连接FTP服务器异常",e);
//        }
//    }