package com.beiming.evidenceplatform.common.enums.evidenceplatform;

/**
 * @author : chenpeng
 * @date : 2018-08-22 03:15 文件是否删除code
 */
public enum StatusCode {
  /**
   * 0 表示文件已经逻辑删除,并放入回收站
   */
  DELETE(0),
  /**
   * 1 表示文件没有被放入回收
   */
  RESTORE(1);

  private int value;

  private StatusCode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
