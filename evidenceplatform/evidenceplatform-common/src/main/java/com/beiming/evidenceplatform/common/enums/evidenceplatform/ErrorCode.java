package com.beiming.evidenceplatform.common.enums.evidenceplatform;

/**
 * @author : chenpeng
 * @date : 2018-08-10 11:06
 */
public enum ErrorCode {
  /**
   * (保留码) 表示未知异常
   */
  UNEXCEPTED(9999),
  /**
   * 成功
   */
  SUCCESS(1000);

  private int value;

  private ErrorCode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
