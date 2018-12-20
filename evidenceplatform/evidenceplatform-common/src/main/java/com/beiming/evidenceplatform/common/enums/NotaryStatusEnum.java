package com.beiming.evidenceplatform.common.enums;

/**
 * @Description:申请执行状态枚举
 * @Author: caiwei
 * @Date: Created 2018/4/26
 */
public enum NotaryStatusEnum {

  APPLY_REVIEW("01", "申请待审核"),
  APPLY_FAIL("02", "申请失败"),
  APPLY_INQUIRY("03", "申请待问询"),
  SIGN("04", "待签名"),
  EXECUTE("05", "待执行"),
  EXECUTE_REVIEW("06", "待执行审核"),
  EXECUTE_FAIL("07", "待执行失败"),
  VIDEO_CONFIRM("08", "待视频确认"),
  APPLY_EXECUTE_COMPLETE("09", "申请执行完成");

  private String code;
  private String name;

  NotaryStatusEnum(String code, String name) {
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
