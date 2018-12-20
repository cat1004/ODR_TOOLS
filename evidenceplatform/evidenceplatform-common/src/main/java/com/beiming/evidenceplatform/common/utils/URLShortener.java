package com.beiming.evidenceplatform.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import com.beiming.evidenceplatform.common.AppException;
import com.beiming.evidenceplatform.common.enums.ErrorCode;

public class URLShortener {

  private static final String DICT = "abcdefghijklmnopqrstuvwxyz"
      + "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  // 数字->字符映射
  private static final char[] CHARS = DICT.toCharArray();
  // 字符->数字映射
  private static final Map<Character, Integer> NUMBERS = new HashMap<>();

  static {
    int len = CHARS.length;
    for (int i = 0; i < len; i++) {
      NUMBERS.put(CHARS[i], i);
    }
  }

  /**
   * 根据从数据库中返回的记录ID生成对应的短网址编码
   *
   * @param id (1-56.8billion)
   */
  public static String encode(long id) {
    StringBuilder shortURL = new StringBuilder();
    while (id > 0) {
      int r = (int) (id % 62);
      shortURL.insert(0, CHARS[r]);
      id = id / 62;
    }
    int len = shortURL.length();
    while (len < 6) {
      shortURL.insert(0, CHARS[0]);
      len++;
    }
    return shortURL.toString();
  }

  /**
   * 根据获得的短网址编码解析出数据库中对应的记录ID
   *
   * @param key 短网址 eg. RwTji8, GijT7Y等等
   */
  public static long decode(String key) {
    char[] shorts = key.toCharArray();
    int len = shorts.length;
    long id = 0l;
    for (int i = 0; i < len; i++) {
      id = id + (long) (NUMBERS.get(shorts[i]) * Math.pow(62, len - i - 1));
    }
    return id;
  }

  public static void main(String[] args) {
//    System.out.println(encode(39729551080l));
//    System.out.println(decode("RwTji8"));
    System.out.println(shortUrl(
        "https://testapi.xihugongzheng.com/video/showQRCode?businessId=1111111111111111&userId=99999999000090909090"));
  }

  public static String shortUrl(String url) {

    // 要使用生成 URL 的字符

    String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",

        "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",

        "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",

        "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",

        "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",

        "U", "V", "W", "X", "Y", "Z"


    };

    // 对传入网址进行 MD5 加密

    String sMD5EncryptResult = getMD5(url);

    String hex = sMD5EncryptResult;

    String resUrl = new String();

    // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算

    String sTempSubString = hex.substring(8, 8 + 8);

    // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界

    long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);

    String outChars = "";

    for (int j = 0; j < 6; j++) {

      // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引

      long index = 0x0000003D & lHexLong;

      // 把取得的字符相加

      outChars += chars[(int) index];

      // 每次循环按位右移 5 位

      lHexLong = lHexLong >> 5;
    }

    return outChars;

  }

  public static String getMD5(String str) {
    try {
      // 生成一个MD5加密计算摘要
      MessageDigest md = MessageDigest.getInstance("MD5");
      // 计算md5函数
      md.update(str.getBytes());
      // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
      // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
      return new BigInteger(1, md.digest()).toString(16);
    } catch (Exception e) {
      throw new AppException(ErrorCode.UNEXCEPTED);
    }
  }
}