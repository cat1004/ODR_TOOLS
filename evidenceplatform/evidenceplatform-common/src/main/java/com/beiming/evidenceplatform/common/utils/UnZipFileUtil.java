package com.beiming.evidenceplatform.common.utils;

import java.io.File;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解压缩文件
 *
 * @author tp Date : 2018/6/6/006 16:45
 */
public class UnZipFileUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(UnZipFileUtil.class);

  /**
   * @param sourceZip 待解压文件路径
   * @param destDir 解压到的路径
   */
  public static void unZip(String sourceZip, String destDir) {
    //保证文件夹路径最后是"/"或者"\"
    char lastChar = destDir.charAt(destDir.length() - 1);
    if (lastChar != '/' && lastChar != '\\') {
      destDir += File.separator;
    }
    Project p = new Project();
    Expand e = new Expand();
    e.setProject(p);
    e.setSrc(new File(sourceZip));
    e.setOverwrite(false);
    e.setDest(new File(destDir));
        /*
         ant下的zip工具默认压缩编码为UTF-8编码，
         而winRAR软件压缩是用的windows默认的GBK或者GB2312编码
         所以解压缩时要制定编码格式
         */
    e.setEncoding("gbk");
    e.execute();
    //解析完成之后删除压缩包
    File file = new File(sourceZip);
    if (file.exists()) {
      file.delete();
    }
  }


}
