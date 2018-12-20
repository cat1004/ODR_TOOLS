package com.beiming.evidenceplatform.domain;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 竞拍者端-首页数据总览-chart图表实体DTO类
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeChartDTO implements Serializable {

  /**
   * 月份List
   */
  private List<String> monthList;

  /**
   * 去年数据List
   */
  private List<String> lastYearData;

  /**
   * 今年数据List
   */
  private List<String> thisYearData;

}
