package com.beiming.evidenceplatform.common.utils.evidenceplatform;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chen @Description: @Date: Created in 17:55 2018/8/29 @Modified By: 判断文件类型并返回
 */
public class FileFormatUtils {
  private static final String IMG[] = {
      "bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd", "cdr",
      "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf"
  };
  private static final String TXT[] = {
      "txt", "doc", "xls", "ppt", "docx", "hlp", "wps", "html", "dot"
  };
  private static final String MEDIA[] = {
      "wav", "aif", "mp4", "mp3", "rm", "rmvb", "avi", "swf", "au"
  };

  public static String typeCode(String fileName) {
    List<String[]> list = new ArrayList<>();
    list.add(IMG);
    list.add(TXT);
    list.add(MEDIA);
    int conunt = 0;
    Boolean canUploaded = false;
    for (String[] extension : list) {
      conunt++;
      canUploaded = isValid(fileName, extension);
      if (canUploaded) {
        return conunt + "";
      }
    }
    return "1";
  }

  public static boolean isValid(String contentType, String... allowTypes) {
    if (null == contentType || "".equals(contentType)) {
      return false;
    }
    for (String type : allowTypes) {
      if (contentType.indexOf(type) > -1) {
        return true;
      }
    }
    return false;
  }
}