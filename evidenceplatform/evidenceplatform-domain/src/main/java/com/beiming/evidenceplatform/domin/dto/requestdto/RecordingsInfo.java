package com.beiming.evidenceplatform.domin.dto.requestdto;

import java.util.Date;
import lombok.Data;
@Data
public class RecordingsInfo {

  private String type; // 咨询类型
  
  private Date  advicetime; // 咨询时间 
  
  private String advicename; // 咨询人姓名
  
  private String advicephone; // 咨询人号码
  
  private String adviceproblem; // 咨询问题内容
  
  private String url; // 咨询录音附件地址
}
