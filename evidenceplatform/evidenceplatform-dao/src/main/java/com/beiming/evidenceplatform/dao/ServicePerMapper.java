package com.beiming.evidenceplatform.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.beiming.evidenceplatform.domain.ServicePer;
import com.beiming.evidenceplatform.domain.dto.responsedto.ServicePerResponseDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.JudgeLoginUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.ServicePerPersonalDTO;

import tk.mybatis.mapper.common.Mapper;

public interface ServicePerMapper extends Mapper<ServicePer> {
  /**
   * 通过id查询服务人员
   */
  public ServicePer getServicePerById(@Param("id") long id);

  /**
   * 通过手机号查询服务人员
   */
  public ServicePer getServicePersByPhone(@Param("phone") String phone);


  /**
   * 根据orgId和type查询法官
   */
  public List<ServicePerResponseDTO> queryJudgesByOrgIdAndType(@Param("orgId") Long orgId,
      @Param("type") String type);

  /**
   * 添加服务人员
   */
  public Long insertServicePer(ServicePer servicePer);

  /**
   * 通过服务人员id及type查询一个服务人员
   */
  public ServicePerResponseDTO queryServicePerById(@Param("id") long id, @Param("type") String type);

  /**
   * 修改手机号
   */
  public int updatePhoneById(ServicePer servicePer);

  /**
   * 修改密码
   */
  public int updatePasswordById(ServicePer servicePer);

  /**
   * 通过手机号修改密码
   */
  public int updatePasswordByPhone(ServicePer servicePer);

  /**
   * 通过法院id和code查询该法院下的所有法院管理人
   */
  public List<ServicePer> queryAllServicePersByIdAndCode(@Param("id") long id,
      @Param("code") String code);

  public List<ServicePerResponseDTO> queryAllServicePersByIdAndCodeAndSearch(@Param("id") long id,
      @Param("type") String type, @Param("search") String search);

  /**
   * 根据手机号和角色查询对应的角色
   */
  public ServicePerResponseDTO queryServicePersByPhone(@Param("phone") String phone,
      @Param("type") String type);

  /**
   * 根据手机号查询对应的角色
   */
  public List<ServicePer> queryServicePersByPhoneNumber(@Param("phone") String phone);

  /**
   * @description 通过手机号密码查询法院管理人员
   */
  List<ServicePerPersonalDTO> queryCourtManageUsrByPhoneAndPassWord(@Param("phone") String phone,
      @Param("passWord") String passWord);

  /**
   * @description 通过手机号密码查询法官
   */
  List<ServicePerPersonalDTO> queryCourtUsrByPhoneAndPassWord(@Param("phone") String phone,
      @Param("passWord") String passWord);

  /**
   * @description 根据用户id查询用户信息
   */
  List<JudgeLoginUserDTO> queryCourtUsrByUserId(@Param("userId") String userId,
      @Param("userRole") String userRole);

  List<ServicePer> getServicePerByOrgId(long orgId);
}
