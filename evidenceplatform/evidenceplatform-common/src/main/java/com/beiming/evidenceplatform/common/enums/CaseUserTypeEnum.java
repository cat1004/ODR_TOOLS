package com.beiming.evidenceplatform.common.enums;

/**
 * 案件角色
 * @author zhangqi
 */
public enum CaseUserTypeEnum {

  APPLICANT("10", "申请人"), AGENT_APPLICANT("11", "申请人代理人"), RESPONDENT("20", "被申请人"), AGENT_RESPONDENT("21", "被申请人代理人");

  private String code;
  private String name;

  CaseUserTypeEnum(String code, String name) {
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
