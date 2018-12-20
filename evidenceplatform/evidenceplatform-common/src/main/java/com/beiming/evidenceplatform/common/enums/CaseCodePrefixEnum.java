package com.beiming.evidenceplatform.common.enums;

/**
 * 案件编号前缀枚举
 */
public enum CaseCodePrefixEnum {

  PERSIONAL_CASE_CODE("C0"),
  BUSINESS_CASE_CODE("B0");

  private String code;

  CaseCodePrefixEnum(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
