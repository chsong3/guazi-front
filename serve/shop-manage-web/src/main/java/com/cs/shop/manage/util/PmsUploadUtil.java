package com.cs.shop.manage.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: csong
 * @create: 2019-11-25
 **/
public class PmsUploadUtil {
    public static String uploadImage(MultipartFile multipartFile){
        String fileUrl="http://106.54.28.115";
        //长传到服务器
        //配置fdfs的全局链接地址
        String path = PmsUploadUtil.class.getResource("/tracker.conf").getPath();//获得配置文件路径

        try {
            ClientGlobal.init(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        TrackerClient trackerClient=new TrackerClient();
        TrackerServer trackerServer=null;
        //获得一个trackerServer的实例
        try {
            trackerServer = trackerClient.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //通过tracker获得一个Storage链接客户端
        StorageClient storageClient = new StorageClient(trackerServer, null);
        try {
            byte[] bytes = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            int index = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(index+1);
            String[] uploadFiles = storageClient.upload_file(bytes, extName, null);
            for (String file : uploadFiles) {
                fileUrl +="/"+file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return fileUrl;
    }
}
