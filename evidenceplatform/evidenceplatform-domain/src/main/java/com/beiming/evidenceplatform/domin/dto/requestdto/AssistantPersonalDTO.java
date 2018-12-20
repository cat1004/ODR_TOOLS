package com.beiming.evidenceplatform.domin.dto.requestdto;

import lombok.Data;

/**
 * Created by xiaoqiang on 2018/7/6.
 */
@Data
public class AssistantPersonalDTO {

  /**
   * 辅助人员id
   */
  private long id;
  /**
   * 真实姓名
   */
  private String actualName;

  /**
   * 密码
   */
  private String password;

  /**
   * 电话号码
   */
  private String phone;

  /**
   * 身份证号码
   */
  private String idCard;
  /**
   * 状态
   */
  private String status;
  /**
   * 状态
   */
  private String headImageUrl;

}
