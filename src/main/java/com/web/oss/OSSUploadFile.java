package com.web.oss;


import com.aliyun.oss.OSSClient;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 阿里云上传文件
 * @author chxy
 */
public class OSSUploadFile {


    /**
     * 上传文件
     * @param file
     * @return
     */
    public static String uploadFile(File file){

        OSSClient ossClient = new OSSClient(OSSConfig.END_POINT, OSSConfig.AccessKeyId, OSSConfig.AccessKeySecret);
        String filename = file.getName();
        String houzui = filename.split("\\.")[filename.split("\\.").length-1];
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String date = df.format(new Date());
        Random random = new Random();
        String objectKey = date+ random.nextInt(10000);
        ossClient.putObject(OSSConfig.BucketName, objectKey, file);
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        String url = ossClient.generatePresignedUrl(OSSConfig.BucketName, objectKey, expiration).toString();
        ossClient.shutdown();
        return url;
    }

    public static void main(String[] args) {
        File file = new File("test.txt");
        System.out.println(file.canRead());
        uploadFile(file);
    }

}
