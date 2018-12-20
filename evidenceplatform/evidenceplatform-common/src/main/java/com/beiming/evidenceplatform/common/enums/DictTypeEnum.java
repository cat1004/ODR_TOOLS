package com.beiming.evidenceplatform.common.enums;

/**
 * @Description:字典表类型
 * @author weibo
 */
public enum DictTypeEnum {

  DISPUTE("dispute_type", "纠纷类型");

  private String code;
  private String name;

  DictTypeEnum(String code, String name) {
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
