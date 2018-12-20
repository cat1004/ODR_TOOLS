package com.beiming.evidenceplatform.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加密工具类
 * 
 * md5加密出来的长度是32位
 * 
 */
public class Encrypt {
  private static Logger logger = LoggerFactory.getLogger(Encrypt.class);

  /**
   * 测试
   * 
   * @param args
   */
  public static void main(String[] args) {
    // md5加密测试
    String md51 = md5("1111");
    String md52 = md5("中国");
    // System.out.println(md5_1 + "\n" + md5_2);

  }

  /**
   * md5加密
   * 
   * @param inputText
   * @return
   */
  public static String md5(String inputText) {
    return encrypt(inputText);
  }

  /**
   * md5或者sha-1加密
   * 
   * @param inputText
   *          要加密的内容
   * @param algorithmName
   *          加密算法名称：md5或者sha-1，不区分大小写
   * @return
   */
  private static String encrypt(String inputText) {
    if (inputText == null || "".equals(inputText.trim())) {
      throw new IllegalArgumentException("请输入要加密的内容");
    }
    String encryptText = null;
    try {
      MessageDigest m = MessageDigest.getInstance("md5");
      m.update(inputText.getBytes("UTF8"));
      byte s[] = m.digest();
      // m.digest(inputText.getBytes("UTF8"));
      return hex(s);
    } catch (NoSuchAlgorithmException e) {
      logger.error("Encrypt encrypt error", e);
    } catch (UnsupportedEncodingException e) {
      logger.error("Encrypt encrypt error", e);
    }
    return encryptText;
  }

  /**
   * 返回十六进制字符串
   * 
   * @param arr
   * @return
   */
  private static String hex(byte[] arr) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < arr.length; ++i) {
      sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString();
  }

}
