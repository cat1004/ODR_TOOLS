package com.beiming.evidenceplatform.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @Description: 公证员
 * @Author: caiwei
 * @Date: Created 2018-05-02
 */
@Data
public class NotaryUserDTO implements Serializable {

  private static final long serialVersionUID = 3479180682406555634L;

  // 公证员id
  private Long notaryId;

  // 公证员名称
  private String notaryName;

  // 公证员手机号
  private String mobilePhone;
}
