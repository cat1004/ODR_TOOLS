package com.beiming.evidenceplatform.common.enums;

/**
 * 银行或公证列表时间排序，时间区间
 * 
 * @author qsh
 * @date 2018年5月4日
 */
public enum NotaryListEnum {

  COMMON_START_TIME("00:00:00"), COMMON_END_TIME("23:59:59"),
  COMMON_SECOND_START_STR(":00"), COMMON_SECOND_END_STR(":59"),
  CREATE_TIME_SORTFIELD("create_time"), UPDATE_TIME_SORTFIELD("update_time"), BOOKING_TIME_SORTFIELD(
      "booking_time");
  private String code;

  public String getCode() {
    return code;
  }

  private NotaryListEnum(String code) {
    this.code = code;
  }

}
