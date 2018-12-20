package com.beiming.evidenceplatform.common.enums;
/**
 * <pre>
 * 文档地址
 * https://cloud.tencent.com/document/api/267/5957
 * event_type = 100 代表有新的录制文件生成
 * 此枚举为event_type = 100时,腾讯给的消息体中的key
 * @author ljf</pre>
 *
 */
public enum EventTypeIs100Enum {
  
  /**
   * <pre>点播用 vid，在点播平台可以唯一定位一个点播视频文件
   * </pre>
   */
  VIDEO_ID("video_id"),
  
  /**
   * <pre>下载地址
   * </pre>
   */
  VIDEO_URL("video_url"),
  /**
   * <pre>文件大小
   * </pre>
   */
  FILE_SIZE("file_size"),
  /**
   * <pre>分片开始时间戳
   * </pre>
   */
  START_TIME("start_time"),
  /**
   * <pre>分片结束时间戳   
   * </pre>
   */
  END_TIME("end_time"),
  /**
   * <pre>file_id
   * </pre>
   */
  FILE_ID("file_id"),
  /**
   * <pre>文件格式
   * </pre>
   */
  FILE_FORMAT("file_format"),
  /**
   * <pre>是否开启点播 2.0
   * 0 表示未开启，1 表示开启
   * </pre>
   */
  VOD2FLAG("vod2Flag"),
  /**
   * <pre>录制文件 ID
   * </pre>
   */
  RECORD_FILE_ID("record_file_id"),
  /**
   * <pre>推流时长
   * </pre>
   */
  DURATION("duration"),
  /**
   * <pre>
   * 推流 url 参数
   * </pre>
   */
  STREAM_PARAM("stream_param");
  
  
  private String value;

  private EventTypeIs100Enum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
