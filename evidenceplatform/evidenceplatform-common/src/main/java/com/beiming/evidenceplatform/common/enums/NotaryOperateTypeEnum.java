package com.beiming.evidenceplatform.common.enums;

public enum NotaryOperateTypeEnum {
  NOTARY_APPLY("1", "申请"),
  NOTARY_EXECUTE("2", "执行");

  private String code;
  private String name;

  NotaryOperateTypeEnum(String code, String name) {
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
