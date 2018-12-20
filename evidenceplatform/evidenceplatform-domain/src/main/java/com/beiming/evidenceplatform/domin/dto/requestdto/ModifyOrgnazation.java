package com.beiming.evidenceplatform.domin.dto.requestdto;

import lombok.Data;

/**
 * 
 * @author zhaomx
 * @date 2018年7月5日
 * @description 修改机构(法院)接收参数DTO
 */
@Data
public class ModifyOrgnazation {

  /**
   * 机构id
   */
  private Long orgnazationId;
  /**
   * 地区id
   */
  private String areaId;
  /**
   * 机构名称
   */
  private String orgnaztionName;
}
