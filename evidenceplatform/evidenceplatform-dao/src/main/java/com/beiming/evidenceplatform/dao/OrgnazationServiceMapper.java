package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.OrgnazationService;

import tk.mybatis.mapper.common.Mapper;

public interface OrgnazationServiceMapper extends Mapper<OrgnazationService> {

  public void insertOrgSer(OrgnazationService orgnazationService);

  /**
   * 修改服务人员状态
   */
  public void updateServicePerStatus(OrgnazationService orgnazationService);
}
