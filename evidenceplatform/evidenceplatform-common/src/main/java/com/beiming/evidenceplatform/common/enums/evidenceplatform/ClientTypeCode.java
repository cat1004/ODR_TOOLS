package com.beiming.evidenceplatform.common.enums.evidenceplatform;

/**
 * @author : chenpeng
 * @date : 2018-08-22 14:58 客户端类型
 */
public enum ClientTypeCode {
  //hash文件类型
  LOADUPLOAD0(0);
  private int value;

  private ClientTypeCode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
