package com.beiming.evidenceplatform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 按月分组的标的物总量实体类DTO
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorporeCountByMonth {

  private String month;
  private String corporeCountByMonth;

}
