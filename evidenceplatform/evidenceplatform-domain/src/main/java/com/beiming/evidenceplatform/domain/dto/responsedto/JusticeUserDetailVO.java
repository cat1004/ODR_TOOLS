package com.beiming.evidenceplatform.domain.dto.responsedto;

import lombok.Data;

/**
 * Created by xiaoqiang on 2018/7/3.
 */
@Data
public class JusticeUserDetailVO {
  private String name; //用户真实姓名
  private boolean passWordIsUp; //用户密码
  private String phone; //用户账号或手机号
  private String organizationName; //用户所在组织

}
