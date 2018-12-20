package com.beiming.evidenceplatform.domin.dto.requestdto;

import lombok.Data;

/**
 * 
 * @author zhaomx
 * @date 2018年7月5日
 * @description 修改手机号码
 */
@Data
public class ModifyServicePerPassword {
  /**
   * 服务人员id
   */
  private Long id;
  /**
   * 密码
   */
  private String password;
}
