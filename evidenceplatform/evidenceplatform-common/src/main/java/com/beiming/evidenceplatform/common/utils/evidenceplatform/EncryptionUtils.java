package com.beiming.evidenceplatform.common.utils.evidenceplatform;

/**
 * @author : chenpeng
 * @date : 2018-08-17 14:03 提供MD5 SHA1 的加密方式计算hash
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class EncryptionUtils {
  private static final String SHA1 = "SHA1";
  private static final String MD5 = "MD5";


  private static final Logger LOGGER = LoggerFactory.getLogger(EncryptionUtils.class);

  /**
   * 计算字符串的hash值
   *
   * @param str    明文
   * @param algorithm 算法名
   * @return 字符串的hash值
   */
  public static String stringHash(String str, String algorithm) {
    LOGGER.info("开始计算字符串hash,传入的参数为string:" + str + ",alg:" + algorithm);
    if (str.isEmpty()) {
      return "";
    }
    MessageDigest hash = null;
    try {
      hash = MessageDigest.getInstance(algorithm);
      byte[] bytes = hash.digest(str.getBytes("UTF-8"));
      String result = "";
      for (byte b : bytes) {
        String temp = Integer.toHexString(b & 0xff);
        if (temp.length() == 1) {
          temp = "0" + temp;
        }
        result += temp;
      }
      return result;
    } catch (NoSuchAlgorithmException e) {
      LOGGER.info("计算字符串hash出现为问题,异常是", e);
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      LOGGER.info("计算字符串hash出现为问题,异常是", e);
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 计算文件的hash值
   *
   * @param file      文件File
   * @param algorithm 算法名
   * @return 文件的hash值
   */
  public static String fileHash(final File file, String algorithm) throws NoSuchAlgorithmException, IOException {
    final MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
    LOGGER.info("开始计算文件hash,参数是file:" + file + ",algorithm:" + algorithm);
    try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
      final byte[] buffer = new byte[1024 * 1024 * 10];
      for (int read = 0; (read = is.read(buffer)) != -1; ) {
        messageDigest.update(buffer, 0, read);
      }
    }

    // Convert the byte to hex format
    try (Formatter formatter = new Formatter()) {
      for (final byte b : messageDigest.digest()) {
        formatter.format("%02x", b);
      }
      return formatter.toString();
    }
  }
}
