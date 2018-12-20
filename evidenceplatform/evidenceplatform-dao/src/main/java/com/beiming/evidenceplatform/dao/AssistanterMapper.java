package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.Assistanter;
import com.beiming.evidenceplatform.domain.dto.responsedto.AssistanterResponseDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.AssistantPersonalDTO;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhaomx
 * @date 2018年7月4日
 * @description 辅助人员DAO
 */
public interface AssistanterMapper extends Mapper<Assistanter> {

  /**
   * @description 查询所有辅助人员
   */
  public List<AssistanterResponseDTO> queryAllAssistanter();

  /**
   * @description 通过手机号查询辅助人员
   */
  public Assistanter queryAssistanterByPhone(@Param("phone") String phone);

  /**
   * @description 通过id查询辅助人员
   */
  public AssistanterResponseDTO queryAssistanterById(@Param("id") long id);

  /**
   * @description 模糊查询辅助人员
   */
  public List<AssistanterResponseDTO> queryAssistanterByName(String name);

  /**
   * @description 添加辅助人员
   */
  public Long insertAssistanter(Assistanter assistanter);

  /**
   * @description 修改手机号
   */
  public void updatePhoneById(Assistanter assistanter);

  /**
   * @description 修改密码
   */
  public void updatePasswordById(Assistanter assistanter);

  /**
   * @description 修改状态
   */
  public void updateStatus(Assistanter assistanter);

  /**
   * @description 通过手机号密码查询辅助人员
   */
  List<Assistanter> queryAssistantUsrByPhoneAndPassWord(@Param("userId") String userId,
      @Param("phone") String phone,
      @Param("password") String password);
  /**
   * @description 通过手机号密码查询辅助人员
   */
  List<AssistantPersonalDTO> queryAssistantPersonal(@Param("userId") String userId,
      @Param("phone") String phone,
      @Param("password") String password);
  public int updatePasswordByUserId(@Param("uerId") long userId, @Param("password") String password);
  /**
   * @description 通过用户手机号更新用户密码
   */
  public int updatePasswordByPhone(@Param("phone") String phone, @Param("password") String password);
}
