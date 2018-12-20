package com.beiming.evidenceplatform.common.enums;

/**
 * 案件进度状态-工作人员
 *
 * @author zhangqi
 */
public enum ArbitrationCaseLogWorkStatusEnum {

  APPLY_REVIEW("申请立案"),
  APPLY_REJECT("办案秘书 不予立案"),
  APPLY_MODIFY("办案秘书提交 补正立案"),
  APPLY_SUC("已经立案,已经发送仲裁费定审单,受理通知书,举证通知书"),

  EXECUTE_ARBITRATOR_QUE("仲裁员发送问题单"),
  CC_ARBITRATOR_REVIEW_SUC("仲裁员结案审核通过"),
  CC_SECRETARY_REVIEW_SUC("办案秘书结案审核通过"),
  CC_LEADER_REVIEW_SUC("领导结案审核通过"),
  FILE_APP("办案秘书 归档申请"),
  FILE_REVIEW_SUC("领导 归档审核通过");

  private String name;

  public String getName() {
    return name;
  }

  private ArbitrationCaseLogWorkStatusEnum(String name) {
    this.name = name;
  }

}
