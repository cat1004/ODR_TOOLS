package com.beiming.evidenceplatform.common.enums;

/**
 * 公共查询字段
 * 
 * @author zhangqi
 */
public enum ArbitrationCaseBaseEnum {

  COMMON_START_TIME("00:00:00"), COMMON_END_TIME("23:59:59"),
  COMMON_SECOND_START_STR(":00"), COMMON_SECOND_END_STR(":59"),
  CREATE_TIME_SORTFIELD("create_time"), UPDATE_TIME_SORTFIELD("update_time");
  private String code;

  public String getCode() {
    return code;
  }

  private ArbitrationCaseBaseEnum(String code) {
    this.code = code;
  }

}
