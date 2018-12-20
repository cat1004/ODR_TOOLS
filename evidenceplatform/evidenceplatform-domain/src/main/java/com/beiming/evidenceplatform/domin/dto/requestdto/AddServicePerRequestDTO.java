package com.beiming.evidenceplatform.domin.dto.requestdto;

import lombok.Data;

/**
 * 
 * @author zhaomx
 * @date 2018年7月5日
 * @description 添加服务人员接收参数DTO
 */
@Data
public class AddServicePerRequestDTO {

  /**
   * 姓名
   */
  private String name;
  /**
   * 身份证号码
   */
  private String idCard;
  /**
   * 手机号码
   */
  private String phone;
  /**
   * 机构id 所属法院id
   */
  private Long orgnazationId;
}
