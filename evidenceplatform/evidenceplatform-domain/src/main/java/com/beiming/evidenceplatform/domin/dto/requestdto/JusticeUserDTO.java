package com.beiming.evidenceplatform.domin.dto.requestdto;

/**
 * Created by xiaoqiang on 2018/6/28.
 */

import lombok.Data;

@Data
public class JusticeUserDTO {
  private String name;
  private String passWord;
  private String phone;
  private String userId;
  private String token;
}
