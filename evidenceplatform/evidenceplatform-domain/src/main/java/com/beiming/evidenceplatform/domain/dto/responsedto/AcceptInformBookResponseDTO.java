package com.beiming.evidenceplatform.domain.dto.responsedto;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @Auther: tyrion
 * @Date: 2018/6/11 17:22
 * @类描述:
 */
@Data
public class AcceptInformBookResponseDTO {

  /**
   * 案件id
   */
  private String caseId;

  /**
   * 用户名称
   */
  private String userName;

  /**
   * 用户类型，关联CaseUserTypeEnum枚举
   */
  private String userType;

  /**
   * 用户userId
   */
  private String userId;

  /**
   * 案件编号
   */
  private String caseNo;

  /**
   * 案件名称
   */
  private String caseName;

  /**
   * 纠纷类型 关联CaseApplyEnum枚举
   */
  private String disputeType;
  /**
   * 案件涉及金额,标的额
   */
  private BigDecimal targetAmount;

  /**
   * 案件处理费
   */
  private BigDecimal disposeAmount;
  /**
   * 案件受理费
   */
  private BigDecimal acceptAmount;
}
