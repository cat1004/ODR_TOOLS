package com.beiming.evidenceplatform.domin.dto.requestdto;

import lombok.Data;

/**
 * Created by xiaoqiang on 2018/7/5.
 */
@Data
public class BidderForgetPwdDTO {
  private String mobilePhone; //手机号
  private String password; //新密码
  private String verificationCode; //验证码

}
