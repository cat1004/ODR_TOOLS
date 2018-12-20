package com.beiming.evidenceplatform.domin.dto.requestdto;

import lombok.Data;

/**
 * 
 * @author zhaomx
 * @date 2018年7月5日
 * @description 法官列表接收参数DTO
 */
@Data
public class ServicePerListRequestDTO {

  /**
   * 法院机构id
   */
  private Long orgnazationId;
  /**
   * 搜索条件
   */
  private String search;
}
