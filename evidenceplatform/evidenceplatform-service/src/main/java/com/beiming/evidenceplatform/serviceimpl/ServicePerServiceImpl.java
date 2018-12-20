package com.beiming.evidenceplatform.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beiming.evidenceplatform.dao.AdmintorMapper;
import com.beiming.evidenceplatform.dao.ServicePerMapper;
import com.beiming.evidenceplatform.domain.Admintor;
import com.beiming.evidenceplatform.domain.ServicePer;
import com.beiming.evidenceplatform.domain.dto.responsedto.ServicePerResponseDTO;
import com.beiming.evidenceplatform.service.ServicePerService;

/**
 * 
 * @author zhaomx
 * @date 2018年7月4日
 * @description 服务人员ServiceImpl
 */
@Service
public class ServicePerServiceImpl implements ServicePerService {

  @Autowired
  private ServicePerMapper servicePerDao;

  @Autowired
  private AdmintorMapper admintorDao;

  @Override
  public Long addServicePer(ServicePer servicePer) {
    // TODO Auto-generated method stub
    return servicePerDao.insertServicePer(servicePer);
  }

  @Override
  public ServicePerResponseDTO getServicePerById(long id, String type) {
    // TODO Auto-generated method stub
    return servicePerDao.queryServicePerById(id, type);
  }

  @Override
  public int updatePhoneById(ServicePer servicePer) {
    // TODO Auto-generated method stub
    return servicePerDao.updatePhoneById(servicePer);
  }

  @Override
  public int updatePasswordById(ServicePer servicePer) {
    // TODO Auto-generated method stub
    return servicePerDao.updatePasswordById(servicePer);
  }

  @Override
  public List<ServicePerResponseDTO> getServicesPerByIdAndCodeAndSearch(long id, String code,
      String search) {
    // TODO Auto-generated method stub
    return servicePerDao.queryAllServicePersByIdAndCodeAndSearch(id, code, search);
  }

  @Override
  public ServicePerResponseDTO getServicesByPhone(String phone, String code) {
    // TODO Auto-generated method stub
    return servicePerDao.queryServicePersByPhone(phone, code);
  }

  @Override
  public ServicePer getServicePerByPhone(String phone) {
    // TODO Auto-generated method stub
    return servicePerDao.getServicePersByPhone(phone);
  }

  @Override
  public ServicePer getServicePerById(long id) {
    // TODO Auto-generated method stub
    return servicePerDao.getServicePerById(id);
  }

  @Override
  public List<ServicePerResponseDTO> getServicesPersByIdAndCode(long id, String code) {
    // TODO Auto-generated method stub
    List<ServicePerResponseDTO> servicePers = servicePerDao.queryJudgesByOrgIdAndType(id, code);
    if (servicePers.size() == 0 || servicePers == null) {
      return null;
    } else {
      return servicePers;
    }
  }

  @Override
  public Admintor getAdmintorById(long id) {
    // TODO Auto-generated method stub
    return admintorDao.queryAdmintorById(id);
  }

  @Override
  public List<ServicePerResponseDTO> getServicePerByOrgId(long orgId) {
    return servicePerDao.queryJudgesByOrgIdAndType(orgId, "120003");
  }
}
