package com.beiming.evidenceplatform.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @ClassName RealConditionDTO
 * @Description:TODO
 * @author:zhangfucheng
 * @date
 */
@Data
public class RealConditionDTO implements Serializable {

  private long id; //主键
  private String orderTime; // 预约看样时间
  private String problems; // 问题描述

  private String name; //看样顾问的名字
  private String status; //新增或者编辑的状态  new---新增  edit---编辑

  private String url; //图片的url
}
