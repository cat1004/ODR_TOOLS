package com.beiming.evidenceplatform.domain.dto.responsedto;

import java.util.Date;
import lombok.Data;

/**
 * 
 * @author zhaomx
 * @date 2018年7月2日
 * @description 机构响应dto
 */
@Data
public class OrgnazationResponseDTO {
  /**
   * 机构id
   */
  private Long id;
  /**
   * 地区id
   */
  private String areaId;
  /**
   * 机构名称
   */
  private String orgnazationName;
  /**
   * 创建时间
   */
  private Date createTime;
  /**
   * 省名称
   */
  private String provinceName;
  /**
   * 市名称
   */
  private String cityName;
}
