package com.beiming.evidenceplatform.domain;

import lombok.Data;

@Data
public class UserRoleRelation  {
  /**
   * 用户id
   */
  private String userId;

  /**
   * 角色id
   */
  private String roleId;
}
