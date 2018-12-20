package com.beiming.evidenceplatform.common.utils;

import com.beiming.evidenceplatform.common.Assert;
import com.beiming.evidenceplatform.common.constants.LibraConst;
import com.beiming.evidenceplatform.common.enums.ErrorCode;

import java.io.File;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.regex.Pattern;

public class CommonsUtils {

  /**
   * 单位、量词定义
   */
  public static final String PRICE_UNIT_SPLIT = "元";
  public static final String HOUSE_AREA_UNIT = "平方米";
  public static final String HOUSE_AREA_UNIT_SPLIT = "平";
  public static final String TIME_UNIT = "小时";
  public static final String HOUSE_TYPE = "110001";
  public static final String HOUSE_AVG_PRICE_UNIT = "元/平方米";

  /**
   * <pre>
   * 创建目录 如果存在则忽略,不存在则创建 创建失败则抛出异常{@link }
   *
   * @param dirPath
   * @throws SecurityException If a security manager exists and its <code>{@link
   *          java.lang.SecurityManager#checkRead(java.lang.String)}</code> method does not permit
   *         verification of the existence of the named directory and all necessary parent
   *         directories; or if the <code>{@link
   *          java.lang.SecurityManager#checkWrite(java.lang.String)}</code> method does not permit
   *         the named directory and all necessary parent directories to be created
   *
   *         AppException 当输入参数为空或空字符串
   * </pre>
   */
  public static void createDirs(String dirPath) {
    Assert.isNotEmpty(dirPath, ErrorCode.ILLEGAL_PARAMETER);
    File f = new File(dirPath);
    if (!f.exists()) {
      f.mkdirs();
    }
  }

  /**
   * 获取一个不包含'-'符号的32位长度的UUID
   */
  public static String get32BitUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  /**
   * <PRE>
   * 将文件大小单位转换成带单位的形式响应给调用者,并保留2为小数 最大单位为GB eg: 1.00B 2.30KB 2.30MB 2.30GB
   *
   * @throws size不是非负整数时抛出 <code>{@link com.beiming.evidenceplatform.common.AppException}</code>
   * </PRE>
   */
  public static String getPrintSize(String size) {
    Assert.isNumeric(size, ErrorCode.ILLEGAL_PARAMETER);
    long sizeLong = Long.valueOf(size);
    return getPrintSize(sizeLong);
  }

  public static boolean isNumeric(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isDigit(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * <PRE>
   * 将文件大小单位转换成带单位的形式响应给调用者,并保留2为小数 最大单位为GB eg: 1.00B 2.30KB 2.30MB 2.30GB
   */
  public static String getPrintSize(long size) {
    BigDecimal newBig = new BigDecimal(size);
    BigDecimal big1024 = BigDecimal.valueOf(1024);
    // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
    if (big1024.compareTo(newBig) > -1) {
      return newBig.setScale(2, BigDecimal.ROUND_UP).toString() + "B";
    } else {
      newBig = newBig.divide(big1024);
    }
    // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
    // 因为还没有到达要使用另一个单位的时候
    // 接下去以此类推
    if (big1024.compareTo(newBig) > -1) {
      return newBig.setScale(2, BigDecimal.ROUND_UP).toString() + "KB";
    } else {
      newBig = newBig.divide(big1024);
    }
    if (big1024.compareTo(newBig) > -1) {
      // 因为如果以MB为单位的话，要保留最后1位小数，
      // 因此，把此数乘以100之后再取余
      return newBig.setScale(2, BigDecimal.ROUND_UP).toString() + "MB";
    } else {
      // 否则如果要以GB为单位的，先除于1024再作同样的处理
      newBig = newBig.divide(big1024);
      return newBig.setScale(2, BigDecimal.ROUND_UP).toString() + "GB";
    }
  }

  /**
   * 验证手机号（精确）
   *
   * @param input 待验证文本
   * @return {@code true}: 匹配<br> {@code false}: 不匹配
   */
  public static boolean isMobileExact(CharSequence input) {
    return isMatch(LibraConst.REGEX_MOBILE_EXACT, input);
  }

  private static boolean isMatch(String regex, CharSequence input) {
    return input != null && input.length() > 0 && Pattern.matches(regex, input);
  }
}
