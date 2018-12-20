package com.beiming.evidenceplatform.common.utils.evidenceplatform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.jar.Pack200.Packer.ERROR;
import static java.util.jar.Pack200.Packer.TRUE;

public class FileUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

  /**
   * @param: [fileName, url, response] fileName 文件名称 url：文件路径
   */
  public static String getFileFromLocalhostToHash(
      String alg,
      String hash,
      String fileName,
      String path,
      String cosUrl,
      HttpServletResponse response,
      HttpServletRequest request)
      throws IOException, NoSuchAlgorithmException {
    LOGGER.info("接受到的参数分别是:" + "alg:" + alg + ",hash:" + hash + ",path:" + path + ",cosUrl" + cosUrl + ".");
    File file = new File(path);

    // 如果文件名存在，则进行下载
    String fileHash = EncryptionUtils.fileHash(file, alg);
    if (!file.isFile()) {
      return ERROR;
    }
    if (!fileHash.equals(hash)) {
      return ERROR;
    }

    return getFileFromLocal(fileName, path, response, request);
  }

  // path中的
  public static String getFileFromLocal(String fileName, String path, HttpServletResponse response, HttpServletRequest request)
      throws IOException {
    LOGGER.info("接受到的参数分别是:" + "fileName:" + fileName + ",path:" + path);
    LOGGER.info("开始处理用户下载请求,此请求不计算hash");
    String browser = (String) request.getHeader("USER-AGENT"); // 获取文件名. 需要判断浏览器类型, 非火狐的编码方式是UTF-8编码，否则会出文件名乱码
    fileName = getFileNameAccordingToBrowser(fileName, path, browser);
    File file = new File(path);

    String type = path.substring(path.lastIndexOf(".") + 1).toLowerCase(); //从path中拿ext
    // 设置响应头部
    BufferedInputStream bis = null;
    OutputStream os = null;
    response.reset();
    response.setCharacterEncoding("utf-8");
    if ("docx".equals(type)) {
      response.setContentType("application/msword"); // word格式
    } else if ("pdf".equals(type)) {
      response.setContentType("application/pdf"); // pdf格式
    } else if ("zip".equals(type)) {
      response.setContentType("application/zip"); // zip
    } else {
      response.setContentType("application/octet-stream"); // 其他格式
    }

    response.setHeader(
        "Content-Disposition", "attachment;filename=" + fileName + "." + type);

    // 将文件流写入response
    try {
      LOGGER.info("使用文件流写入response");
      bis = new BufferedInputStream(new FileInputStream(file));
      byte[] b = new byte[bis.available() + 1000];
      int i = 0;
      os = response.getOutputStream(); // 直接下载导出
      while ((i = bis.read(b)) != -1) {
        os.write(b, 0, i);
      }
      os.flush();
      System.out.println("Download the song successfully!");
    } catch (Exception e) {
      LOGGER.error("文件流写入失败,错误的是异常是:", e);
      return ERROR;
    } finally {
      bis.close();
      os.close();
      LOGGER.info("关闭流");
    }
    return TRUE;
  }

  public static String getFileSize(Long length) {
    LOGGER.info("接受到的参数分别是:" + "length:" + length);
    DecimalFormat df1 = new DecimalFormat("0.00");
    String fileSizeString = "";
    long fileSize = length;
    if (fileSize < 1024) {
      fileSizeString = df1.format((double) fileSize) + "B";
    } else if (fileSize < 1048576) {
      fileSizeString = df1.format((double) fileSize / 1024) + "KB";
    } else if (fileSize < 1073741824) {
      fileSizeString = df1.format((double) fileSize / 1048576) + "MB";
    } else {
      fileSizeString = df1.format((double) fileSize / 1073741824) + "G";
    }
    LOGGER.info("文件大小算完成,结果:" + fileSizeString);
    return fileSizeString;
  }

  private static String getFileNameAccordingToBrowser(String fileName, String url, String browser) {
    LOGGER.info("接受到的参数分别是:" + "fileName:" + fileName + ",url:" + url + ",browser:" + browser);
    // 获取文件名. 需要判断浏览器类型, 非火狐的编码方式是UTF-8编码，否则会出文件名乱码
    File file = new File(url);
    if (fileName.contains(".")) {
      fileName = fileName.substring(0, fileName.lastIndexOf("."));
    }
    try {
      LOGGER.info("开始处理浏览器编码问题");
      if (browser != null && browser.indexOf("Fireforx") != -1) {
        fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
      } else {
        fileName = URLEncoder.encode(fileName, "UTF-8");
      }
    } catch (Exception e) {
      LOGGER.error("下载文件名编码错误{}", e);
      e.printStackTrace();
    }
    return fileName;
  }
}
