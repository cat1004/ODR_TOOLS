package com.beiming.evidenceplatform.common.enums;
/**
 * 腾讯事件消息通知公共头信息
 * @author ljf
 *
 */
public enum VideoNoticeMessageHeadEnum {
  /**
   * 有效时间
   */
  T("t"),
  /**
   * <pre>
   * 安全签名
   * MD5(KEY+t)
   * </pre>
   */
  SIGN("sign"),
  /**
   * 事件类型   目前可能值为： 0、1、100、200
   */
  EVENT_TYPE("event_type"),
  /**
   * 直播码
   */
  STREAM_ID("stream_id"),
  /**
   * 直播码
   */
  CHANNEL_ID("channel_id");
  
  
  private String value;

  private VideoNoticeMessageHeadEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
