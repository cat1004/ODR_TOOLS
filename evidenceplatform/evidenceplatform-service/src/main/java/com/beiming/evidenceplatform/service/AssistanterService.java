package com.beiming.evidenceplatform.service;

import java.util.List;

import com.beiming.evidenceplatform.domain.Assistanter;
import com.beiming.evidenceplatform.domain.dto.responsedto.AssistanterResponseDTO;

/**
 * 
 * @author zhaomx
 * @date 2018年7月4日
 * @description 辅助人员service
 */
public interface AssistanterService {
  /**
   * 查询所有辅助人员
   */
  public List<AssistanterResponseDTO> getAllAssistanter();

  /**
   * 根据手机号查询辅助人员
   */
  public Assistanter getAssistanterByPhone(String phone);

  /**
   * 通过id查询辅助人员
   */
  public AssistanterResponseDTO getAssistanterById(long id);

  /**
   * 模糊查询辅助人员
   */
  public List<AssistanterResponseDTO> getAssistantersByName(String name);


  /**
   * 添加辅助人员
   */
  public Long addAssistanter(Assistanter assistanter);

  /**
   * 修改手机号
   */
  public void modifyPhoneById(Assistanter assistanter);

  /**
   * 修改密码
   */
  public void modifyPasswordById(Assistanter assistanter);

  /**
   * 修改状态
   */
  public void modifyStatus(Assistanter assistanter);
}
