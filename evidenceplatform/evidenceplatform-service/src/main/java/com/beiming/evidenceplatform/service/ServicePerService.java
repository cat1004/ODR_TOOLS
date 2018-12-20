package com.beiming.evidenceplatform.service;

import java.util.List;

import com.beiming.evidenceplatform.domain.Admintor;
import com.beiming.evidenceplatform.domain.ServicePer;
import com.beiming.evidenceplatform.domain.dto.responsedto.ServicePerResponseDTO;

/**
 * 
 * @author zhaomx
 * @date 2018年7月3日
 * @description 服务人员Service
 */
public interface ServicePerService {
  /**
   * 通过id查询服务人员
   */
  public ServicePer getServicePerById(long id);

  /**
   * 添加服务人员
   */
  public Long addServicePer(ServicePer servicePer);

  /**
   * 通过服务人员id及type查询
   */
  public ServicePerResponseDTO getServicePerById(long id, String type);

  /**
   * 修改手机号
   */
  public int updatePhoneById(ServicePer servicePer);

  /**
   * 修改密码
   */
  public int updatePasswordById(ServicePer servicePer);

  /**
   * 通过手机号查询服务人员
   */
  public ServicePer getServicePerByPhone(String phone);

  /**
   * 通过法院id和code查询该法院下所有对应的服务人员
   */
  public List<ServicePerResponseDTO> getServicesPersByIdAndCode(long id, String code);

  /**
   * 模糊查询,搜索
   */
  public List<ServicePerResponseDTO> getServicesPerByIdAndCodeAndSearch(long id, String code,
      String search);

  /**
   * 通过手机号查询对应的角色
   */
  public ServicePerResponseDTO getServicesByPhone(String phone, String code);
  
  /**
   * 通过id查询系统管理员
   */
  public Admintor getAdmintorById(long id);


  /**
   * 根据法院id获取法官列表
   * @param orgId
   * @return
   */
  List<ServicePerResponseDTO> getServicePerByOrgId(long orgId);
}
