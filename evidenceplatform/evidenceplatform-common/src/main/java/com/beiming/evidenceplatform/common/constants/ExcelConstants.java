package com.beiming.evidenceplatform.common.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tp Date : 2018/6/8/008 14:24
 */
public class ExcelConstants {

  public static Map<String, Object> constantMap = new HashMap<>();

  static {
    constantMap.put("时间节点", "time");
    constantMap.put("卡号", "cardNo");
    constantMap.put("账号", "accountNo");
    constantMap.put("身份证号", "code");
    constantMap.put("本金", "principal");
    constantMap.put("费用", "fee");
    constantMap.put("利息", "interests");
    constantMap.put("客户姓名", "userName");
    constantMap.put("逾期天数", "overdueDays");
    constantMap.put("手机", "mobilePhone");
    constantMap.put("账单地址", "permanentAddress");
    constantMap.put("e-mail地址", "email");
    constantMap.put("开户日期", "openAccountDay");
    constantMap.put("分期标识", "installmentType");
    constantMap.put("账单日", "billDate");
    constantMap.put("性别", "sex");
    constantMap.put("民族", "civilFamily");
    constantMap.put("提请仲裁时计算日", "dateOfArbitration");
    constantMap.put("合同签订日", "dateOfContract");
    constantMap.put("合同名称", "contactName");
    constantMap.put("合同编号", "contactNo");
    constantMap.put("归档编号", "archive");
  }

  /**
   * 卡号
   */
  public static final String CARDNO = "cardNo";
  /**
   * 账号
   */
  public static final String ACCOUNTNO = "accountNo";
  /**
   * 身份证号
   */
  public static final String CODE = "code";
  /**
   * 本金
   */
  public static final String PRINCIPAL = "principal";
  /**
   * 费用
   */
  public static final String FEE = "fee";
  /**
   * 利息
   */
  public static final String INTERESTS = "interests";
  /**
   * 电话
   */
  public static final String MOBILEPHONE = "mobilePhone";
  /**
   * 邮寄地址
   */
  public static final String MAILADDRESS = "email";
  /**
   * 擅长类型
   */
  public static final String WELLDO = "借贷纠纷";
  /**
   * 案件申请请求事项拼装
   */
  public static final String REQUEST_ITEM = "1.裁决被申请人向申请人支付本金及利息%s元，其中借款本金%s元,借款利息（含逾期利息）%s元（利息、逾期利息暂计至%s，此后按合同约定的利率及逾期利率支付正常利息及逾期利息至付清欠款之日止）。<br/>2.本案仲裁费用（包括财产保全费用）由被申请人承担。";

  /**
   * 事实与理由
   */
  public static final String FACTANDREASON =
      "申请人与被申请人于%s签订《%s》一份，合同号为%s,双方约定：借款金额为%s元整，借款用途为个人消费。双方约定：自乙方发放贷款次月起，每1个月为1期，共36期，甲方应从第1期开始按期偿还借款本金，每期归还本金1000元，还款日为每期的%s日"
          + "；末期即贷款实际到期日归还借款本金10000元<br>";
  /**
   * 模板文件中excel文件名称
   */
  public static final String CASEXLS = "信息总表.xlsx";

  public static final String TITLE = "案件书面申请成功";

  public static final String MESSAGES = "您相关联的案件【%s】,已提交审核";
  /**
   * 办案秘书
   */
  public static final String SECRET_MESSAGES = "您相关联的案件【%s】,请审核";
}
