package com.beiming.evidenceplatform.domain.dto;

import lombok.Data;

/**
 * @ClassName SurveyDTO
 * @Description:TODO
 * @author:zhangfucheng
 * @date
 */
@Data
public class SurveyDTO {

  private long id;
  /**
   * 房产id
   */
  private long corporeHouseId;
  /**
   * 勘验人员
   */
  private String name;

  /**
   * 水费
   */
  private String waterCharges;

  /**
   * 电费
   */
  private String elecCharges;

  /**
   * 物业费
   */
  private String propertyFees;

  /**
   * 税费
   */
  private String taxes;

  /**
   * 租用情况
   */
  private String rent;

  /**
   * 占用情况
   */
  private String occupy;

  /**
   * 图片key
   */
  private String url;
}
