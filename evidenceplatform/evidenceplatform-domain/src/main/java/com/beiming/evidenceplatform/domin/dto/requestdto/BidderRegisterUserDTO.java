package com.beiming.evidenceplatform.domin.dto.requestdto;

import lombok.Data;

/**
 * Created by xiaoqiang on 2018/7/3.
 */
@Data
public class BidderRegisterUserDTO {
  private String mobilePhone; //手机号
  private String password; //密码
  private String verificationCode; //验证码
}
