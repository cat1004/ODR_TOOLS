package com.beiming.evidenceplatform.domain.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 字典表DTO
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/6/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictDTO implements Serializable {

  private static final long serialVersionUID = -4597591409911241946L;

  private Long id;

  private String name;

  private String code;

  private String describ;

  private String extend;

  private String type;

}
