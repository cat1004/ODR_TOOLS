package com.beiming.evidenceplatform.domin.dto.requestdto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 作者
 * @version 创建时间：2018年8月27日 下午12:32:26 类说明
 */

@ApiModel("修改绑定手机号dto")
@Data
public class BidderUserPhoneDTO {
  
  private static final long serialVersionUID = 1L;
  @ApiModelProperty(value = "新手机号码", required = true)
  private String newMobilePhone;
  @ApiModelProperty(value = "验证码", required = true)
  private String verificationCode;

}
