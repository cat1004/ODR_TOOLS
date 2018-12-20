package com.beiming.evidenceplatform.common.enums;

/**
 * @Description:申请公证类型枚举
 * @Author: caiwei
 * @Date: Created 2018/4/27
 */
public enum ApplyNotaryTypeEnum {

  PERSIONAL("1", "个人"),
  BUSINESS("2", "企业");

  private String code;
  private String name;

  ApplyNotaryTypeEnum(String code, String name) {
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
