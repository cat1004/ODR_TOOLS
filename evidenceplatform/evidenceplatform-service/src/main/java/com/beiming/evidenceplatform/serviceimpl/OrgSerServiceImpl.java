package com.beiming.evidenceplatform.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beiming.evidenceplatform.dao.OrgnazationServiceMapper;
import com.beiming.evidenceplatform.domain.OrgnazationService;
import com.beiming.evidenceplatform.service.OrgSerService;

/**
 * 
 * @author zhaomx
 * @date 2018年7月4日
 * @description 机构/服务人员中间表
 */
@Service
public class OrgSerServiceImpl implements OrgSerService {


  @Autowired
  private OrgnazationServiceMapper orgService;

  @Override
  public void addOrgService(OrgnazationService orgSer) {
    // TODO Auto-generated method stub
    orgService.insertOrgSer(orgSer);
  }

  @Override
  public void updateServicePerStatus(OrgnazationService orgnazationService) {
    // TODO Auto-generated method stub
    orgService.updateServicePerStatus(orgnazationService);
  }

}
