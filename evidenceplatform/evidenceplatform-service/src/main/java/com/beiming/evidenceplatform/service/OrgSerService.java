package com.beiming.evidenceplatform.service;

import com.beiming.evidenceplatform.domain.OrgnazationService;

public interface OrgSerService {
  public void addOrgService(OrgnazationService orgSer);

  /**
   * 修改服务人员状态
   */
  public void updateServicePerStatus(OrgnazationService orgnazationService);
}
