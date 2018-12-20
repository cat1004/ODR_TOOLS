package com.beiming.evidenceplatform.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zli on 2018/5/8.
 *
 * 文件工具类
 */
public class FileUtil {

  /**
   * MultipartFile to File
   *
   * @param file 文件
   */
  public static File convert(MultipartFile file) throws IOException {
    File convFile = new File(file.getOriginalFilename());
    convFile.createNewFile();
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(file.getBytes());
    fos.close();
    return convFile;
  }

  /**
   * 判断文件是否是图片
   *
   * @param file 文件
   * @return 是图片返回true，不是图片返回false
   */
  public static Boolean isPicture(MultipartFile file) {
    String fileName = file.getOriginalFilename();
    String fileExtension = "";
    fileExtension = fileName.substring(fileName.lastIndexOf("."));
    return ".jpg".equals(fileExtension.toLowerCase())
        || ".png".equals(fileExtension.toLowerCase())
        || ".jpeg".equals(fileExtension.toLowerCase())
        || ".gif".equals(fileExtension.toLowerCase());
  }
}
