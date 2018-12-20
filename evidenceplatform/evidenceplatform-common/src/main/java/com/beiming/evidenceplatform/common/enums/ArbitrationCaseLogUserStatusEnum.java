package com.beiming.evidenceplatform.common.enums;

/**
 * 案件进度状态-用户
 *
 * @author zhangqi
 */
public enum ArbitrationCaseLogUserStatusEnum {

  APPLY_REVIEW("申请立案"),
  APPLY_REJECT("办案秘书 不予立案"),
  APPLY_MODIFY("办案秘书提交 补正立案"),
  APPLY_SUC("已经立案,已经发送仲裁费定审单,受理通知书,举证通知书"),
  EXECUTE_ARBITRATOR_QUE("仲裁员发送问题单"),
  ENDING("待结案"),
  END("结案");

  private String name;

  public String getName() {
    return name;
  }

  private ArbitrationCaseLogUserStatusEnum(String name) {
    this.name = name;
  }

}
