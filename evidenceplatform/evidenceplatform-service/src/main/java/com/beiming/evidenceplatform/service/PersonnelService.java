package com.beiming.evidenceplatform.service;

import com.beiming.evidenceplatform.domain.Personnel;
import com.beiming.evidenceplatform.domin.dto.requestdto.MobilePhoneRequestDTO;

public interface PersonnelService {
  public void addPersonnel(Personnel personnel);
  
  /**
   * 未注册用户获取手机验证码
   * @param dto
   * @return
   */
  public String getPhoneCode(MobilePhoneRequestDTO dto);
  
  /**
   * 注册用户获取手机验证码
   * @param dto
   * @return
   */
  
  public String getSmsCode(MobilePhoneRequestDTO dto);
  
  /**
   * 修改身份状态
   */
  public int modifyPersonnel(Personnel personnel);
}
