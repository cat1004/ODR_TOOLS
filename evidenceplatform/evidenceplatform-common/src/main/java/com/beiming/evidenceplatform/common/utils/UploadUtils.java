package com.beiming.evidenceplatform.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName UploadUtils
 * @Description:TODO
 * @author:zhangfucheng
 * @date
 */
public class UploadUtils {
  public static String reName(String fileName) {
    System.out.print(fileName);
    fileName =  UUID.randomUUID() + getSuffixName(fileName);
    System.out.print(fileName);
    return fileName;
  }
  public static boolean verifyFileName(String suffix) {
    String [] whiteSuffix = new String[]{
        ".png", ".jpg", ".jpeg", ".wma",  ".bmp", ".gif", ".mp3", ".mid", ".doc", ".docx"
    };
    for (int i = 0; i < whiteSuffix.length; i++) {
      if (whiteSuffix[i].equals(suffix)) {
        return true;
      }
    }
    return false;
  }

  public static String getSuffixName(String fileName) {
    String suffix = fileName.substring(fileName.lastIndexOf("."));
    return suffix;
  }

  public static byte[] getContent(File file) throws IOException {
    InputStream is = new FileInputStream(file);
    byte [] content = new byte[is.available()];
    is.read(content);
    is.close();
    return content;
  }
  public static void main(String [] args) throws Exception {
  }
}
