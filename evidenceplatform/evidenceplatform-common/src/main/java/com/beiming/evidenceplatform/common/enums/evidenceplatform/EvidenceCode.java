package com.beiming.evidenceplatform.common.enums.evidenceplatform;

/**
 * @author : chenpeng
 * @date : 2018-08-22 14:58 文件类型
 */
public enum EvidenceCode {
  //hash文件类型
  TEXTEVIDENCE(0);
  private int value;

  private EvidenceCode(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

}
