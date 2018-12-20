package com.beiming.evidenceplatform.domin.dto.requestdto;

import lombok.Data;

/**
 * Created by xiaoqiang on 2018/7/3.
 */
@Data
public class BidderChangePwdUserDTO {
  private String password; //新密码
  private String oldPassword; //原密码

}
