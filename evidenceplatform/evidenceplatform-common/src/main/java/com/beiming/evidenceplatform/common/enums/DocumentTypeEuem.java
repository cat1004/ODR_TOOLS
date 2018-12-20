package com.beiming.evidenceplatform.common.enums;

/**
 * @Auther: tyrion
 * @Date: 2018/6/11 13:50
 * @枚举描述: 文书类型枚举值,所有文书以简易版本为主
 */
public enum DocumentTypeEuem {

  ACCEPT_INFORM_BOOK("01", "受理通知书"),
  PROOF_PROVIDE_BOOK("04", "举证通知书"),
  FEE_APPROVAL_BILL("07", "仲裁费审定单"),
  JUDGE_INFORM_BOOK("10", "应裁通知书"),
  ARBITRATION_APPLY_BOOK("12", "仲裁申请书"),
  ARBITRATION_ANSWER_BOOK("15", "仲裁答辩书"),
  END_CASE_BOOK("18", "结案文书"),
  WITHDRAWN_DECIDE_BOOK("21", "撤案决定书");
  // TODO 未缴费撤案决定书，授权委托书等...


  private String code;

  private String name;

  DocumentTypeEuem(String code, String name) {
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
