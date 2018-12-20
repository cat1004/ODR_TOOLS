package com.beiming.evidenceplatform.domain.dto.responsedto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录成功令牌
 * 
 * @author lb
 *
 */
@Data
@ApiModel(value = "登录成功dto")
public class LoginTokenResponseDTO implements Serializable {

  private static final long serialVersionUID = 940644291147800571L;

  /**
   * 登录用户信息
   */
  @ApiModelProperty(value = "登录用户信息")
  private LoginInfoResponseDTO loginInfo;

  /**
   * 认证令牌
   */
  @ApiModelProperty(value = "认证令牌")
  private String authToken;

  /**
   * 刷新令牌
   */
  @ApiModelProperty(value = "刷新令牌")
  private String refreshToken;

  /**
   * 是否已认证
   */
  @ApiModelProperty(value = "是否已认证")
  private Integer isCertification;

}
