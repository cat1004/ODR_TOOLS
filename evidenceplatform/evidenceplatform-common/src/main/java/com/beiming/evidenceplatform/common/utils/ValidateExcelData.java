package com.beiming.evidenceplatform.common.utils;

import java.util.regex.Pattern;

/**
 * excel中数据验证
 *
 * @author tp Date : 2018/6/12/012 16:40
 */
public class ValidateExcelData {

  /**
   * 正则表达式：验证用户名
   */
  public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";

  /**
   * 正则表达式：验证密码
   */
  public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

  /**
   * 正则表达式：验证手机号
   */
  public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

  /**
   * 正则表达式：验证邮箱
   */
  public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

  /**
   * 正则表达式：验证汉字
   */
  public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

  /**
   * 正则表达式：验证身份证
   */
  public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

  /**
   * 正则表达式：验证URL
   */
  public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

  public static final String REGEX_NUMBER = "^[1-9]\\d*\\.?\\d*$";

  /**
   * 校验用户名
   *
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isUsername(String username) {
    return Pattern.matches(REGEX_USERNAME, username);
  }

  /**
   * 校验密码
   *
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isPassword(String password) {
    return Pattern.matches(REGEX_PASSWORD, password);
  }

  /**
   * 校验手机号
   *
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isMobile(String mobile) {
    return Pattern.matches(REGEX_MOBILE, mobile);
  }

  /**
   * 校验邮箱
   *
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isEmail(String email) {
    return Pattern.matches(REGEX_EMAIL, email);
  }

  /**
   * 校验汉字
   *
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isChinese(String chinese) {
    return Pattern.matches(REGEX_CHINESE, chinese);
  }

  /**
   * 校验身份证
   *
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isIDCard(String idCard) {
    return Pattern.matches(REGEX_ID_CARD, idCard);
  }

  /**
   * 校验URL
   *
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isUrl(String url) {
    return Pattern.matches(REGEX_URL, url);
  }

  /**
   * 校验URL
   *
   * @return 校验通过返回true，否则返回false
   */
  public static boolean isNumber(String number) {
    return Pattern.matches(REGEX_NUMBER, number);
  }

  public static boolean isCardNo(String stringVal) {
    int length = stringVal.length();
    if (length >= 16 && length <= 19) {
      return true;
    }
    return false;
  }
}
