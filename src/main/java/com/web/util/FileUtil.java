package com.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 *
 * 文件上传类
 * @author chxy
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 上传文件
     * @param file
     * @param path
     * @return
     */
    public static String uploadFile(MultipartFile file, String path){
        String name = file.getOriginalFilename();
        String siffixname = name.substring(name.lastIndexOf("."));
        String hash = Integer.toHexString(new Random().nextInt());
        String fileName = hash + siffixname;
        // String uploadDir = request.getSession().getServletContext().getRealPath("/")+path;
        //String uploadDir = "src/main/resources/static/upload";
        String uploadDir = "/Users/chenxiaoyang/Downloads/java/uploadImg";
        File tempFile = new File(uploadDir, fileName);
        if (!tempFile.getParentFile().exists()){
            tempFile.getParentFile().mkdir();
        }
        if (tempFile.exists()){
            tempFile.delete();
        }
        try {
            tempFile.createNewFile();
            file.transferTo(tempFile);
        } catch (IOException e) {
            logger.error("创建文件失败!"+e);
        }
        return tempFile.getPath();
    }

    /**
     * 转file
     * @return
     */
    // TODO: 2018/10/10 转文件问题
    public static File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static File transferTo(MultipartFile file) throws IOException {
        File convFile = new File( file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
