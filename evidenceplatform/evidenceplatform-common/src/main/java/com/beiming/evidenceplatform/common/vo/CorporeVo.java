package com.beiming.evidenceplatform.common.vo;

import java.sql.Date;
import lombok.Data;

/**
 * 委托标的物列表 查询条件
 * 
 * @author chenpeng
 * 
 */
@Data
public class CorporeVo {
  private Long id;
  private String name;
  private String no; // 标的物标号
  private Date auctiontime;
  private String areaName; // 所在地点
  private String orgName;
  private String type; // 标的类型
  private String status; // 拍卖状态
  private String groundingtype; // 是下架状态
  private Long orgId; // 机构id
}
