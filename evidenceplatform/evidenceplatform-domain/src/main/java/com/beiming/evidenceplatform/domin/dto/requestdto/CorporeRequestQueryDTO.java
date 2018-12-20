package com.beiming.evidenceplatform.domin.dto.requestdto;

import lombok.Data;

/**
 * @Description: 获取标的物列表查询条件DTO
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/6/28
 */
@Data
public class CorporeRequestQueryDTO {

  /**
   * 组织机构ID
   */
  private Long orgId;

  /**
   * 标的物名称
   */
  private String corporeName;

  /**
   * 标的物类型
   */
  private String corporeType;

  /**
   * 标的物所在地区ID
   */
  private String areaId;

  /**
   * 低价格
   */
  private String minPrice;

  /**
   * 高价格
   */
  private String maxPrice;

  /**
   * 标的物状态
   */
  private String corporeStatus;

  /**
   * 天数
   */
  private String day;


}
