package com.beiming.evidenceplatform.domain.dto.responsedto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.CollectionUtils;

import com.beiming.evidenceplatform.domain.Role;
import com.beiming.evidenceplatform.domain.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "登录用户信息dto")
public class LoginInfoResponseDTO implements Serializable {
  private static final long serialVersionUID = 1939993907517003148L;
  @ApiModelProperty(value = "用户id")
  private Long userId;
  @ApiModelProperty(value = "登录名")
  private String userName;
  @ApiModelProperty(value = "用户类型")
  private String userType;
  @ApiModelProperty(value = "是否实名认证")
  private Boolean isRealNameVerify;
  @ApiModelProperty(value = "角色")
  private List<String> roles;
  @ApiModelProperty(value = "用户头像图片链接")
  private String headImageUrl;

  public LoginInfoResponseDTO() {

  }

  public LoginInfoResponseDTO(User user, List<Role> roleList) {
    if (user != null) {
      this.userId = user.getId();
      this.userName = user.getName();
      // this.userType = user.getUserType();
    }
    if (!CollectionUtils.isEmpty(roleList)) {
      roles = new ArrayList<>();
      roleList.forEach(role -> {
        roles.add(role.getRoleCode());
      });
    }
  }
}
