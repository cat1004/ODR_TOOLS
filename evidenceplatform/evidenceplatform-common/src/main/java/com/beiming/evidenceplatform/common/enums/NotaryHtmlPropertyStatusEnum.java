package com.beiming.evidenceplatform.common.enums;

/**
 * @Description:公证表单属性必填
 * @Author: caiwei
 * @Date: Created 2018/5/17
 */
public enum NotaryHtmlPropertyStatusEnum {
  blank("0", "非必填"),
  not_blank("1", "必填");

  private String code;
  private String name;

  NotaryHtmlPropertyStatusEnum(String code, String name) {
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
