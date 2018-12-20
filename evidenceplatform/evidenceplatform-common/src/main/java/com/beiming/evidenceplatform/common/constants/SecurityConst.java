package com.beiming.evidenceplatform.common.constants;

/**
 * token加密的私钥
 * 
 * @author axq
 * @date 2018年6月28日
 */
public class SecurityConst {

  /**
   * 发行者
   */
  public static final String ISSUER = "beiming";
  /**
   * 主题
   */
  public static final String SUBJECT = "verify";
  /**
   * 主题
   */
  public static final String ASSISTANT = "verify";
  /**
   * token放入head头中的名称
   */
  public static final String JWT_AUTH_TOKEN_KEY_NAME = "judJWTToken";
  /**
   * token放入parameter头中的名称
   */
  public static final String JWT_AUTH_TOKEN_KEY_PARAM = "judJWTToken";
  /**
   * 法官角色
   */
  public static final String ROLE_JUDGE = "JUDGE";
  /**
   * 竞拍者角色
   */
  public static final String ROLE_USER = "USER";
  /**
   * 法院管理人角色
   */
  public static final String ROLE_JUDGE_M = "JUDGE_M";
  /**
   * 系统管理员角色
   */
  public static final String ROLE_SYSADMIN = "SYSADMIN";

  /**
   * 辅助人员
   */
  public static final String ROLE_ASSISTANT = "ASSISTANT";

  /**
   * 个人组织身份标识row,默认标识为个人
   */
  public static final String ROLE_ROW_PERSONAL = "0";

}
