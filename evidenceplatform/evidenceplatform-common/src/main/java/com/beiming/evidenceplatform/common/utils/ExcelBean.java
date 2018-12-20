package com.beiming.evidenceplatform.common.utils;

import lombok.Data;

/**
 * @author tp Date : 2018/6/8/008 14:27
 */
@Data
public class ExcelBean {

  /**
   *  时间节点
   */
  private String time;

  /**
   * 卡号
   */
  private String cardNo;

  /**
   * 账号
   */
  private String accountNo;

  /**
   *  身份证号/机构组织代码
   */
  private String code;
  /**
   * 本金
   */
  private String principal;
  /**
   * 费用
   */
  private String fee;
  /**
   * 利息
   */
  private String interests;
  /**
   *  客户姓名
   */
  private String userName;

  /**
   * 逾期天数
   */
  private String overdueDays;
  /**
   *  用户手机号
   */
  private String mobilePhone;
  /**
   *  账单日地址
   */
  private String permanentAddress;
  /**
   *  邮件地址
   */
  private String email;

  /**
   * 开户日期
   */
  private String openAccountDay;

  /**
   * 分期类型
   */
  private String installmentType;
  /**
   *  账单日
   */
  private String billDate;
  /**
   *  性别
   */
  private String sex;
  /**
   * 民族
   */
  private String civilFamily;

  /**
   * 提请仲裁日计算日
   */
  private String dateOfArbitration;
  /**
   * 合同签订日
   */
  private String dateOfContract;
  /**
   *  合同名称
   */
  private String contactName;
  /**
   *  合同编号
   */
  private String contactNo;
  /**
   *  归档编号
   */
  private String archive;
  /**
   * 进入催收系统时间
   */
  private String collectionSystemTime;

}
