package com.beiming.evidenceplatform.common.enums;

/**
 * 短信类型枚举
 * 
 * @author lb
 *
 */
public enum SMSEnum {

  /**
   * 登录验证码短信
   */
  SMS_LOGIN("0"),

  /**
   * 申请阶段 - 案件审核通过后，给用户发送短信
   */
  SMS_APPLY_REVIEW_SUCCESS_TO_USER("1"),

  /**
   * 申请阶段 - 案件不予立案，给用户发短信
   */
  SMS_APPLY_REJECT_TO_USER("2"),

  /**
   * 申请阶段 - 补正立案，给用户发短信
   */
  SMS_APPLY_MODIFY_TO_USER("3"),

  /**
   * 被申请人 发送应裁书，仲裁申请书，答辩书文书
   */
  SMS_BEFORE_FILE_TO_RESPONDENT("4"),

  /**
   * 申请阶段 - 银行人员发起视频问询时，给公证员发送短信
   */
  SMS_APPLY_INQUIRY_TO_NOTARY("5"),

  /**
   * 申请阶段 - 问询完成后，给用户发送签名短信
   */
  SMS_APPLY_USER_SIGN("6"),

  /**
   * 申请阶段 - 申请成功后，给银行职员发短信
   */
  SMS_APPLY_SUCCESS_TO_BANK("7"),

  /**
   * 申请阶段 - 申请成功后，给用户发短信
   */
  SMS_APPLY_SUCCESS_TO_USER("8"),


  /**
   * 执行阶段 - 材料审核通过后，给银行人员发送短信
   */
  SMS_EXECUTE_REVIEW_SUCCESS_TO_BANK("9"),

  /**
   * 执行阶段 - 银行人员发起视频问询时，给公证员发送短信
   */
  SMS_EXECUTE_INQUIRY_TO_NOTARY("10"),

  /**
   * 执行阶段 - 申请执行成功，给银行职员发送短信
   */
  SMS_EXECUTE_SUCCESS_TO_BANK("11"),

  /**
   * 执行阶段 - 申请执行成功，给用户发送短信
   */
  SMS_EXECUTE_SUCCESS_TO_USER("12"),

  /**
   * 执行阶段 - 申请执行驳回，给银行人员发送短信
   */
  SMS_EXECUTE_FAILD_TO_BANK("13");

  private String code;

  private SMSEnum(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
