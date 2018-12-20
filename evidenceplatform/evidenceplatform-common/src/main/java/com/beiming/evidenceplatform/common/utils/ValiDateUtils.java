package com.beiming.evidenceplatform.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValiDateUtils {
  // 验证输入的人名
  public static boolean validatename(String name) {
    if (!name.matches("[\u4e00-\u9fa5]{2,4}")) {
      return false;
    }

    return true;
  }
  
  // 验证是否手机号码
  public static boolean validatephone(String phone) {

    String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
    Pattern p = Pattern.compile(regExp);
    Matcher m = p.matcher(phone);
    return m.find();
  }
}
