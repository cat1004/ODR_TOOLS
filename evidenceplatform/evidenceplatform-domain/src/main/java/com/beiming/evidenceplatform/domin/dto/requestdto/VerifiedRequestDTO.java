package com.beiming.evidenceplatform.domin.dto.requestdto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("实名认证dto")
@Data
public class VerifiedRequestDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  @ApiModelProperty(value = "姓名", required = true)
  private String name;
  @ApiModelProperty(value = "身份证号", required = true)
  private String idCard;
  @ApiModelProperty(value = "手机号码", required = true)
  private String mobilePhone;
  @ApiModelProperty(value = "验证码", required = true)
  private String verificationCode;
}
