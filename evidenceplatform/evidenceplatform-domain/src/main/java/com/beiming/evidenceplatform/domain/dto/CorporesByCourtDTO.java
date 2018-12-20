package com.beiming.evidenceplatform.domain.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 法院列表对应的展示实体DTO
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CorporesByCourtDTO implements Serializable {

  private int count;
  private List<CorporeInfoDTO> corporeInfoDTOList;

}
