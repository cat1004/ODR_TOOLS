package com.beiming.evidenceplatform.common.enums;

/**
 * 实名认证枚举类型
 * @author weibo
 *
 */
public enum VerifyEnum {

  NO("0", "否"), YES("1", "是");

  private String code;
  private String name;

  VerifyEnum(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }
}
