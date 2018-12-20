package com.beiming.evidenceplatform.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 数据总览-按月分组的实体基类
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseGroupByMonthDTO implements Serializable {

  /**
   * 月份
   */
  private String month;

  /**
   * 每月对应的数量
   */
  private String groupByNum;

}
