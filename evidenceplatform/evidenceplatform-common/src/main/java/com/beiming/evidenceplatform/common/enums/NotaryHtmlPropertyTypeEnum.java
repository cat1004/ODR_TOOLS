package com.beiming.evidenceplatform.common.enums;

/**
 * @Description: 公证属性对应表单的类型
 * @Author: caiwei
 * @Date: Created 2018-05-04
 */
public enum  NotaryHtmlPropertyTypeEnum {

  TEXT_BOX("01", "文本框"),
  DROP_DOWN_BOX("02", "下拉框"),
  CHECK_BOX("03", "复选框"),
  RADIO_BOX("04", "单选框"),
  TEXT_AREA("05", "文本域"),
  DATE_BOX("06", "日期"),
  FILE_BOX("07", "文件");

  private String code;
  private String name;

  NotaryHtmlPropertyTypeEnum(String code, String name) {
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
