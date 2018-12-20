package com.beiming.evidenceplatform.common.enums;

/**
 * @Description:状态枚举类型
 * @Author: zhengyu
 * @Date: Created 2018/4/28
 */
public enum ProofStatusEnum {
  //这里0表示正常举证周期内的数据;4表示正常周期外上传并且仲裁员审核通过的数据;两种数据都可作为正常的举证质证信息;
  USED("0", "使用"), DELETE("1", "删除"), UNCHECKED("2", "待审核"), REJECTED("3", "驳回"), CHECKED("4", "审核通过");

  private String code;
  private String name;

  ProofStatusEnum(String code, String name) {
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
