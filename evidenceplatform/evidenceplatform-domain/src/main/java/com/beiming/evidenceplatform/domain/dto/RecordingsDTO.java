package com.beiming.evidenceplatform.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @ClassName RecordingsDTO
 * @Description:TODO
 * @author:zhangfucheng
 * @date
 */
@Data
public class RecordingsDTO implements Serializable {

  private String url; //文件的key

  private Long id;

  private Long corporeHouseId;

  /**
   * 咨询类型
   */
  private String type;
  /**
   * 咨询姓名
   */
  private String adviceName;

  /**
   * 咨询电话
   */
  private String advicePhone;

  /**
   * 咨询问题
   */
  private String adviceProblem;

  private String createUser;

  private String adviceTime;
}
