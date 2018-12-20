package com.beiming.evidenceplatform.domin.dto.requestdto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("手机号dto")
@Data
public class MobilePhoneRequestDTO implements Serializable {

  private static final long serialVersionUID = -2623541251595677383L;
  @ApiModelProperty(value = "手机号", required = true)
  private String mobilePhone;
}
