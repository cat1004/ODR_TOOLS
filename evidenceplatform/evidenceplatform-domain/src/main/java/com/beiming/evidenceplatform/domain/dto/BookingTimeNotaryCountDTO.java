package com.beiming.evidenceplatform.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @Description: 特定日期各个预约时间段的数量分布
 * @Author: caiwei
 * @Date: Created 2018-05-07
 */
@Data
public class BookingTimeNotaryCountDTO implements Serializable {

  private static final long serialVersionUID = -4597591409911241946L;

  /**
   * 公证员数量
   */
  private Integer notaryNum;

  /**
   * 预约时间
   */
  private String timeStr;

}
