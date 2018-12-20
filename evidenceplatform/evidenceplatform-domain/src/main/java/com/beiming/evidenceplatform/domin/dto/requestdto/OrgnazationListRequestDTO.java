package com.beiming.evidenceplatform.domin.dto.requestdto;

import lombok.Data;

/**
 * 
 * @author zhaomx
 * @date 2018年7月5日
 * @description 机构(法院)列表接收参数DTO
 */
@Data
public class OrgnazationListRequestDTO {
  /**
   * 机构id
   */
  private Long areaId;
  /**
   * 搜索条件
   */
  private String search;
}
