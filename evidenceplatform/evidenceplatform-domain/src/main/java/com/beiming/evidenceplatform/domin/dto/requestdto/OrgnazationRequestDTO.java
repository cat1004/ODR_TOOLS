package com.beiming.evidenceplatform.domin.dto.requestdto;

import lombok.Data;

/**
 * 
 * @author zhaomx
 * @date 2018年7月2日
 * @description 机构接收参数DTO
 */
@Data
public class OrgnazationRequestDTO {
  /**
   * 地区id
   */
  private Long areaId;
  /**
   * 机构名称
   */
  private String orgnaztionName;
}
