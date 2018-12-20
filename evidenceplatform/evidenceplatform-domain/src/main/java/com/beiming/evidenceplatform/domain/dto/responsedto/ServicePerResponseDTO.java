package com.beiming.evidenceplatform.domain.dto.responsedto;

import java.util.Date;
import lombok.Data;

@Data
public class ServicePerResponseDTO {
  /**
   * id
   */
  private long id;
  /**
   * 姓名
   */
  private String name;
  /**
   * 法院id
   */
  private long orgId;
  /**
   * 法院名称
   */
  private String courtName;
  /**
   * 电话
   */
  private String phone;
  /**
   * 身份证号码
   */
  private String idCard;
  /**
   * 入库时间
   */
  private Date createTime;
  /**
   * 状态
   */
  private String status;
}
