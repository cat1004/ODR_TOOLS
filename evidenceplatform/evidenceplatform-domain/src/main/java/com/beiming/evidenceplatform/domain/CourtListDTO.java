package com.beiming.evidenceplatform.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/9
 */
@Data
public class CourtListDTO implements Serializable {

  private String provinceName;

  private String provinceNameCode;

  private List<Map<String, Object>> courtList;

}
