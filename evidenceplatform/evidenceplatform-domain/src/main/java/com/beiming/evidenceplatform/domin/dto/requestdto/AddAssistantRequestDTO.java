package com.beiming.evidenceplatform.domin.dto.requestdto;

import lombok.Data;

/**
 * 
 * @author zhaomx
 * @date 2018年7月5日
 * @description 添加辅助人员
 */
@Data
public class AddAssistantRequestDTO {
  /**
   * 名称
   */
  public String name;
  /**
   * 身份证号码
   */
  public String idCard;
  /**
   * 电话
   */
  public String phone;
}
