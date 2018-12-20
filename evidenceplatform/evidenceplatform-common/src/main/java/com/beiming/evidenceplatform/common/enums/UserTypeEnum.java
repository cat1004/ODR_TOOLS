package com.beiming.evidenceplatform.common.enums;

/**
 * @Description:用户类型枚举值
 * @author weibo
 */
public enum UserTypeEnum {

  PEOPLE("0", "个人"), COMPANY("1", "企业"), SPECIAL("2", "专项用户"), SECRETARY("3", "办案秘书"), ARBITRATION("4",
      "仲裁员"), FINANCE("5", "财务"), LEADER("6", "领导"), ADMIN("7", "系统管理员");

  private String code;
  private String name;

  UserTypeEnum(String code, String name) {
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
