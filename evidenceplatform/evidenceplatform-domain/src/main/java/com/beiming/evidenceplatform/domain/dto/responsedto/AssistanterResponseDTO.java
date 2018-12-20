package com.beiming.evidenceplatform.domain.dto.responsedto;

import java.util.Date;
import lombok.Data;

/**
 * 
 * @author zhaomx
 * @date 2018年6月28日
 * @description 辅助人员响应dto
 */
@Data
public class AssistanterResponseDTO {
  /**
   * 辅助人员id
   */
  private Long id;
  /**
   * 辅助人员名称
   */
  private String name;
  /**
   * 手机号码
   */
  private String phone;
  /**
   * 身份证号
   */
  private String idCard;
  /**
   * 创建时间
   */
  private Date createTime;
  /**
   * 状态
   */
  private String status;
}
