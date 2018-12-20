package com.beiming.evidenceplatform.domin.dto.requestdto;

import lombok.Data;

/**
 * Created by xiaoqiang on 2018/7/6.
 */
@Data
public class ServicePerPersonalDTO {
  private Long userId; //用户标识id
  private String actualName; //用户真实姓名
  private String phone; //用户账号或手机号
  private String status; //用户是否被禁用
  private String socialIdentify; //服务人员类型，组织还是个人

  private String role; //用户身份标识

  private boolean statusIsBanned; //用户是否被禁用

  private String headImageUrl; //用户头像图片链接

}
