package com.beiming.evidenceplatform.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/***
 * 大数据工具类<br/>
 * BigDecimal类型的数据，mybatis查询时，只判断是否为null即可；
 * 注意!!如果需要保证精度，最好是不要使用BigDecimal的double参数的构造函数，因为存在损失double参数精度的可能，最好是使用BigDecimal的String参数的构造函数。最好是杜绝使用BigDecimal的double参数的构造函数。
 * @author tp
 *
 */
public final class BigDecimalUtils {

  public static boolean equal(BigDecimal bd1, BigDecimal bd2) {
    if (bd1 == null || bd2 == null) {
      return false;
    }
    return bd1.doubleValue() == bd2.doubleValue();
  }

  /**
   * 大于
   */
  public static boolean greaterThan(BigDecimal bd1, BigDecimal bd2) {
    return greaterThan(bd1, bd2, false);
  }

  /**
   * 如果买入价大于等于 卖出价
   *
   * @param bd1 买入
   * @param bd2 卖出
   * @param equal 是否要等于
   */
  public static boolean greaterThan(BigDecimal bd1, BigDecimal bd2,
      boolean equal) {
    if (bd1 == null || bd2 == null) {
      return false;
    } else {
      if (equal) {
        return bd1.doubleValue() >= bd2.doubleValue();
      } else {
        return bd1.doubleValue() > bd2.doubleValue();
      }
    }
  }

  /**
   * 小于
   */
  public static boolean lessThan(BigDecimal bd1, BigDecimal bd2) {
    return lessThan(bd1, bd2, false);
  }

  /**
   * 小于等于
   */
  public static boolean lessThan(BigDecimal bd1, BigDecimal bd2, boolean equal) {
    if (bd1 == null || bd2 == null) {
      return false;
    } else {
      if (equal) {
        return bd1.doubleValue() <= bd2.doubleValue();
      } else {
        return bd1.doubleValue() < bd2.doubleValue();
      }
    }
  }

  public static BigDecimal max(BigDecimal... bds) {
    BigDecimal max = bds[0];
    for (int i = 1; i < bds.length; i++) {
      if (greaterThan(bds[i], max)) {
        max = bds[i];
      }
    }
    return max;
  }

  public static BigDecimal min(BigDecimal... bds) {
    BigDecimal min = bds[0];
    for (int i = 1; i < bds.length; i++) {
      if (lessThan(bds[i], min)) {
        min = bds[i];
      }
    }
    return min;
  }

  public static BigDecimal sum(BigDecimal... bds) {
    BigDecimal sum = BigDecimal.ZERO;
    for (int i = 0; i < bds.length; i++) {
      if (bds[i] != null) {
        sum = sum.add(bds[i]);
      }
    }
    return sum;
  }

  /**
   * 提供精确的小数位四舍五入处理。
   *
   * @param v 需要四舍五入的数字
   * @param scale 小数点后保留几位
   * @return 四舍五入后的结果
   */
  public static BigDecimal round(BigDecimal v, int scale) {
    if (scale < 0) {
      throw new IllegalArgumentException(
          "The scale must be a positive integer or zero");
    }
    return v.setScale(scale, RoundingMode.HALF_UP);
  }

  /**
   * 提供精确的小数位四舍五入处理。
   *
   * @param v 需要四舍五入的数字
   * @param scale 小数点后保留几位
   * @return 四舍五入后的结果
   */
  public static BigDecimal div12(BigDecimal v) {
    return v.divide(new BigDecimal("12"), 2, BigDecimal.ROUND_HALF_UP);
  }

  /**
   * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
   *
   * @param v1 被除数
   * @param v2 除数
   * @param scale 表示表示需要精确到小数点以后几位。
   * @return 两个参数的商
   */

  public static double div(double v1, double v2, int scale) {
    if (scale < 0) {
      throw new IllegalArgumentException(
          "The   scale   must   be   a   positive   integer   or   zero");
    }
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  public static BigDecimal remainder(BigDecimal v1, BigDecimal v2) {
    if (v1 == null || v2 == null || v2.doubleValue() == 0) {
      throw new IllegalArgumentException("Invalid Params");
    }
    BigDecimal[] results = v1.divideAndRemainder(v2);
    return results[1];
  }

  public static double forAdd(double[] v1, int scale) {
    if (scale < 0) {
      throw new IllegalArgumentException(
          "The   scale   must   be   a   positive   integer   or   zero");
    }
    double tmp = 0.0;
    for (int i = 0; i < v1.length; i++) {
      tmp = add(tmp, v1[i]);
    }
    return mul(tmp, 1, scale);
  }

  public static double minusNumber(double v1) {
    if (v1 <= 0.0) {
      return v1;
    } else {
      return 0.0;
    }
  }

  public static BigDecimal abs(BigDecimal v1) {
    if (lessThan(v1, BigDecimal.ZERO)) {
      return v1.multiply(new BigDecimal("-1"));
    } else {
      return v1;
    }
  }

  /**
   * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
   *
   * @param v1 被除数
   * @param v2 除数
   * @param scale 表示表示需要精确到小数点以后几位。
   * @return 两个参数的商
   */
  public static double div(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.divide(b2).doubleValue();
  }

  /**
   * 提供精确的加法运算。
   *
   * @param v1 被加数
   * @param v2 加数
   * @return 两个参数的和
   */
  public static double add(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.add(b2).doubleValue();
  }
  /**
   * 提供精确的加法运算。
   *
   * @param v1 被加数
   * @param v2 加数
   * @return 两个参数的和
   */
  public static BigDecimal add(String v1, String v2) {
    BigDecimal b1 = new BigDecimal(v1);
    BigDecimal b2 = new BigDecimal(v2);
    return b1.add(b2);
  }

  /**
   * 提供精确的减法运算。
   *
   * @param v1 被减数
   * @param v2 减数
   * @return 两个参数的差
   */
  public static double sub(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.subtract(b2).doubleValue();
  }

  /**
   * 提供精确的乘法运算。
   *
   * @param v1 被乘数
   * @param v2 乘数
   * @return 两个参数的积
   */
  public static double mul(double v1, double v2, int scale) {
    if (scale < 0) {
      throw new IllegalArgumentException(
          "The   scale   must   be   a   positive   integer   or   zero");
    }
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP)
        .doubleValue();
  }

  /**
   * 提供精确的乘法运算。
   *
   * @param v1 被乘数
   * @param v2 乘数
   * @return 两个参数的积
   */
  public static double mul(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    return b1.multiply(b2).doubleValue();
  }

  public static double money(Double v1) {
    if (v1 == null) {
      v1 = 0.0;
    }
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    return b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  public static double money(Double v1, int scale) {
    if (v1 == null) {
      v1 = 0.0;
    }
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    return b1.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  /**
   * 提供精确的类型转换(Float)
   *
   * @param v 需要被转换的数字
   * @return 返回转换结果
   */
  public static float convertsToFloat(double v) {
    BigDecimal b = new BigDecimal(v);
    return b.floatValue();
  }

  /**
   * 提供精确的类型转换(Int)不进行四舍五入
   *
   * @param v 需要被转换的数字
   * @return 返回转换结果
   */
  public static int convertsToInt(double v) {
    BigDecimal b = new BigDecimal(v);
    return b.intValue();
  }

  /**
   * 提供精确的类型转换(Long)
   *
   * @param v 需要被转换的数字
   * @return 返回转换结果
   */
  public static long convertsToLong(double v) {
    BigDecimal b = new BigDecimal(v);
    return b.longValue();
  }

  /*****
   * 往银行发送请求的提现实际金额
   * @return 提现实际金额
   * *********/
  public static String actualWithdrawMoney(BigDecimal amt, String rate,
      double withdrawRatePoundage) {
    BigDecimal withdrawPoundage = new BigDecimal(amt.toString())
        .multiply(new BigDecimal(rate));
    String actualWithdrawMoney = "";
    if (BigDecimalUtils.sub(withdrawPoundage.doubleValue(), withdrawRatePoundage) > 0) {
      actualWithdrawMoney = String
          .valueOf(BigDecimalUtils.sub(amt.doubleValue(), withdrawPoundage.doubleValue()));
    } else {
      //手续费不足两元，按两元计算
      actualWithdrawMoney = String
          .valueOf(BigDecimalUtils.sub(amt.doubleValue(), withdrawRatePoundage));
    }
    return actualWithdrawMoney;
  }

  public static Long withdrawApplyMoney(Long amt, double rate) {
    return new BigDecimal(String.valueOf(amt.toString()))
        .divide(new BigDecimal(String.valueOf(rate))).setScale(2, RoundingMode.HALF_UP).longValue();
  }
}
