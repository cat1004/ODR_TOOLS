package com.beiming.evidenceplatform.domain.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 地区表DTO
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/6/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaDTO implements Serializable {

  private static final long serialVersionUID = -4597591409911241946L;

  private String id;

  /**
   * 省份名称
   */
  private String sName;

  /**
   * 所属地级市名称
   */
  private String lName;

  /**
   * 地区级别
   */
  private Integer level;

  /**
   * 父级地区名称
   */
  private String parentId;

  private Integer cantonFlag;

  /**
   * 是否显示,0为不显示、1位显示
   */
  private Integer showFlag;

  private String name;

}
