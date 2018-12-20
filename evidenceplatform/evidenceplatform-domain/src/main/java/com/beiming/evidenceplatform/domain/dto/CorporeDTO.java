package com.beiming.evidenceplatform.domain.dto;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * @author yanlulu
 * @version 创建时间：2018年6月25日 下午2:16:57 标的物详情中的上方固定位置信息实体
 */
@Data
public class CorporeDTO {

  private String no; // 标的物编号

  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime; // 勘验完成时间

  private String name; // 标的物名称

  private String surveyName; // 勘验人员

  private String orgName; // 执行法院

  private String consultant; // 看样顾问

  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private Date auctionTime; // 拍卖时间

  private String auctionUrl; // 拍卖连接

  private String status; // 拍卖状态

}
