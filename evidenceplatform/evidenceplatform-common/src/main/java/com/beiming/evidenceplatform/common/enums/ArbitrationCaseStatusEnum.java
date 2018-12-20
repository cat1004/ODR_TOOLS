package com.beiming.evidenceplatform.common.enums;

/**
 * 案件状态
 *
 * @Author: zhangqi
 */
public enum ArbitrationCaseStatusEnum {

  APPLY_REVIEW("00", "申请立案"),
  APPLY_CANCEL("09", "取消申请"),
  APPLY_REJECT("02", "不予立案"),
  APPLY_MODIFY("03", "补正立案"),
  APPLY_SUC("04", "立案成功"),
  PAID("11", "已缴费"),
  UNPAID_FAIL("19", "未缴费撤案"),
  EXECUTE_TOOK_ARBITRATOR("20", "已指定仲裁员"),
  EXECUTE_JUSTIFY_OVER("21", "用户举证质证答辩期结束"),
  EXECUTE_ARBITRATOR_QUE("22", "仲裁员问题单"),
  EXECUTE_ARBITRATOR_QUE_OVER("23", "仲裁员问题单结束"),
  CC_ARBITRATOR_REVIEW_SUC("30", "仲裁员结案审核通过"),
  CC_SECRETARY_REVIEW_SUC("31", "办案秘书结案审核通过"),
  CC_LEADER_REVIEW_SUC("32", "领导结案审核通过"),
  FILE_APP("40", "归档申请"),
  FILE_REVIEW_SUC("41", "归档审核通过"),
  MODIFY_CASE_USER("50", "用户追加当事人，变更代理人"),
  ADJUST_APP("60", "用户申请调解"),
  WITHDRAW_CASE_APP("90", "用户申请撤案"),
  WITHDRAW_CASE_ARBITRATOR_REVIEW_SUC("91", "仲裁员撤案审核通过"),
  WITHDRAW_CASE_SECRETARY_REVIEW_SUC("92", "办案秘书撤案审核通过"),
  WITHDRAW_CASE_LEADER_REVIEW_SUC("93", "领导撤案审核通过"),
  WITHDRAW_CASE_FINANCIAL_SUC("94", "财务退款审核通过"),
  WITHDRAW_CASE_LEADER_MONEY_REVIEW_SUC("95", "领导退款审核通过");

  private String code;
  private String name;

  ArbitrationCaseStatusEnum(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }
}
