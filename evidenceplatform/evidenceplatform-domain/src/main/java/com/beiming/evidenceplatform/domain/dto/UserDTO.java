package com.beiming.evidenceplatform.domain.dto;

import lombok.Data;

/**
 * @author 作者
 * @version 创建时间：2018年8月27日 下午12:03:14 类说明
 */
@Data
public class UserDTO {

  private int id;

  private String name;

  private String phone;

  private String email;

  private String actualName;

  private String idCard;

  private int status;

  private String headUrl;

  private String capacity;

  private Integer evidenceNum;

  private String usageAmount;

}
