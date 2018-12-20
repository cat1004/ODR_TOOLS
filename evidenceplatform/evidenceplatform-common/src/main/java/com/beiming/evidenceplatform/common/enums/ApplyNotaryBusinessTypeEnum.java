package com.beiming.evidenceplatform.common.enums;

/**
 * @Description:            申请公证业务类型枚举
 * @Author: caiwei
 * @Date: Created 2018/4/27
 */
public enum ApplyNotaryBusinessTypeEnum {

  HOUSE_LOAN("1", "房贷"),
  CAR_LOAN("2", "车贷"),
  DECORATION_LOAN("3", "装修贷");

  private String code;
  private String name;

  ApplyNotaryBusinessTypeEnum(String code, String name) {
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
