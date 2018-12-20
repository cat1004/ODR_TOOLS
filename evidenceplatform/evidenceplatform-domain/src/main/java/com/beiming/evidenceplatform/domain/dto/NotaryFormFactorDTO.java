package com.beiming.evidenceplatform.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @Description: 公证表单要素
 * @Author: caiwei
 * @Date: Created 2018-05-02
 */
@Data
public class NotaryFormFactorDTO implements Serializable {

  private static final long serialVersionUID = 384730161370328734L;

  // 属性名称
  private String propertyName;

  // 属性key
  private String propertyKey;

  // 属性单位
  private String propertyUnit;

  // 属性类型
  private String propertyType;

  // 属性是否必填
  private String propertyStatus;

  // 属性值
  private String propertyValue;

  public NotaryFormFactorDTO() {

  }

  public NotaryFormFactorDTO(String propertyName, String propertyKey, String propertyUnit,
      String propertyType, String propertyStatus, String propertyValue) {
    this.propertyName = propertyName;
    this.propertyKey = propertyKey;
    this.propertyUnit = propertyUnit;
    this.propertyType = propertyType;
    this.propertyStatus = propertyStatus;
    this.propertyValue = propertyValue;
  }
}
