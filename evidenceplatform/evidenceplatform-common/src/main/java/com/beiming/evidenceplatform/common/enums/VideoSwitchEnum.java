package com.beiming.evidenceplatform.common.enums;
/**
 * 视频是否需要处理开关
 * @author ljf
 *
 */
public enum VideoSwitchEnum {
  /**
   * 处理
   */
  ON("1"),
  /**
   * 不处理
   */
  OFF("0");
  
  
  private String value;

  private VideoSwitchEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
