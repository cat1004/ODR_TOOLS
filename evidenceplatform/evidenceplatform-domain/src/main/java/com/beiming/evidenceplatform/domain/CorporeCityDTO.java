package com.beiming.evidenceplatform.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * @Description: 地级市下标的物的数量DTO类
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/9
 */
@Data
public class CorporeCityDTO implements Serializable {

  private String areaId;
  private String areaName;
  private String corporeCount;

}
