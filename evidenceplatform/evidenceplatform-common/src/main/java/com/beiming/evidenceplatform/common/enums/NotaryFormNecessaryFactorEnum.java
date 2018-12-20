package com.beiming.evidenceplatform.common.enums;

/**
 * @Description:公证表单必要属性
 * @Author: caiwei
 * @Date: Created 2018/5/17
 */
public enum NotaryFormNecessaryFactorEnum {
  USER_NAME("userName", "姓名"),
  MOBILE_PHONE("mobilePhone", "手机号"),
  ID_CARD("idCard", "身份证");

  private String key;
  private String name;

  NotaryFormNecessaryFactorEnum(String key, String name) {
    this.key = key;
    this.name = name;
  }

  public String getKey() {
    return key;
  }

  public String getName() {
    return name;
  }
}
