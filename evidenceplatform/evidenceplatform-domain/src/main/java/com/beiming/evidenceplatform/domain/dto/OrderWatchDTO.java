package com.beiming.evidenceplatform.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.beiming.evidenceplatform.domain.OrderWatchPersonnel;

import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @author yanlulu
 * @version 创建时间：2018年6月26日 上午11:06:22 预约看样中看样安排、预约人数、签到人数封装
 */
@Data
public class OrderWatchDTO {

  private int id; // 预约看样id

  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private Date orderTime; // 看样安排

  private int peopleNum; // 预约人数

  private int comNum; // 签到人数

  private List<OrderWatchPersonnel> orderWatchPersonnels;

  private Long corporeHouseId;

}
