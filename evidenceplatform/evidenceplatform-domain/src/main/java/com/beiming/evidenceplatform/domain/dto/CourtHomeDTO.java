package com.beiming.evidenceplatform.domain.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 竞拍者端-首页法院列表DTO类
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/6/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourtHomeDTO implements Serializable {

  private static final long serialVersionUID = -4597591409911241946L;

  /**
   * 组织机构ID
   */
  private Long orgId;

  /**
   * 法院名称
   */
  private String coutrName;

  /**
   * 法院下标的物的数量
   */
  private int corporeCountOfCourt;

}
