package com.beiming.evidenceplatform.service.usermanage.impl;

import com.beiming.evidenceplatform.common.constants.SecurityConst;
import com.beiming.evidenceplatform.common.constants.StatusType;
import com.beiming.evidenceplatform.dao.AdmintorMapper;
import com.beiming.evidenceplatform.dao.AssistanterMapper;
import com.beiming.evidenceplatform.dao.ServicePerMapper;
import com.beiming.evidenceplatform.domain.Admintor;
import com.beiming.evidenceplatform.domain.Assistanter;
import com.beiming.evidenceplatform.domain.ServicePer;
import com.beiming.evidenceplatform.domin.dto.requestdto.AssistantPersonalDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.AssistantTokenUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.CourtManageTokenUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.ServicePerPersonalDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.SystemTokenUserDTO;
import com.beiming.evidenceplatform.service.usermanage.AssitantUserManage;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaoqiang on 2018/6/28.
 */
@Service
public class AssitantUserManageImpl implements AssitantUserManage {

  @Autowired
  private AssistanterMapper assistanterMapper;

  @Autowired
  private AdmintorMapper admintorMapper;

  @Autowired
  private ServicePerMapper servicePerMapper;


  public AssistantTokenUserDTO assitantUserLogin(String phone, String passWord) {
    if (null == phone || null == passWord) {
      return null;
    }
    AssistantTokenUserDTO assistantTokenUserDTO = new AssistantTokenUserDTO();
    List<AssistantPersonalDTO> assistantPersonalDTOList = assistanterMapper
        .queryAssistantPersonal(null, phone, passWord);
    if (null != assistantPersonalDTOList && assistantPersonalDTOList.size() > 0) {

      List<String> rowList = new ArrayList();
      assistantTokenUserDTO.setUserId(assistantPersonalDTOList.get(0).getId());
      assistantTokenUserDTO.setActualName(assistantPersonalDTOList.get(0).getActualName());
      assistantTokenUserDTO.setHeadImageUrl(assistantPersonalDTOList.get(0).getHeadImageUrl());
      if (StatusType.ON.equals(assistantPersonalDTOList.get(0).getStatus())) {
        assistantTokenUserDTO.setStatusIsBanned(false);
      } else {
        assistantTokenUserDTO.setStatusIsBanned(true);
      }
      assistantTokenUserDTO.setSocialIdentify(SecurityConst.ROLE_ROW_PERSONAL);
      rowList.add(SecurityConst.ROLE_ASSISTANT);

      assistantTokenUserDTO.setTypeList(rowList);
      return assistantTokenUserDTO;
    }
    return null;
  }

  public SystemTokenUserDTO systemUserLogin(String phone, String passWord) {
    if (null == phone || null == passWord) {
      return null;
    }
    SystemTokenUserDTO systemTokenUserDTO = new SystemTokenUserDTO();
    List<Admintor> admintorList = admintorMapper.querySystemUsrByNameAndPassWord(phone, passWord);
    if (null != admintorList && admintorList.size() > 0) {

      List<String> rowList = new ArrayList();
      systemTokenUserDTO.setUserId(admintorList.get(0).getId());
      systemTokenUserDTO.setActualName(admintorList.get(0).getActualName());
      systemTokenUserDTO.setSocialIdentify(SecurityConst.ROLE_ROW_PERSONAL);
      systemTokenUserDTO.setHeadImageUrl(admintorList.get(0).getHeadImageUrl());
      rowList.add(SecurityConst.ROLE_SYSADMIN);

      systemTokenUserDTO.setTypeList(rowList);
      return systemTokenUserDTO;
    }
    return null;
  }

  public CourtManageTokenUserDTO courtManageUserLogin(String phone, String passWord) {
    if (null == phone || null == passWord) {
      return null;
    }
    CourtManageTokenUserDTO courtManageTokenUserDTO = new CourtManageTokenUserDTO();
    List<ServicePerPersonalDTO> servicePerPersonalDTOList = servicePerMapper
        .queryCourtManageUsrByPhoneAndPassWord(phone, passWord);
    if (null != servicePerPersonalDTOList && servicePerPersonalDTOList.size() > 0) {
      List<String> rowList = new ArrayList();
      courtManageTokenUserDTO.setUserId(servicePerPersonalDTOList.get(0).getUserId());
      courtManageTokenUserDTO.setActualName(servicePerPersonalDTOList.get(0).getActualName());
      courtManageTokenUserDTO.setSocialIdentify(SecurityConst.ROLE_ROW_PERSONAL);
      courtManageTokenUserDTO.setHeadImageUrl(servicePerPersonalDTOList.get(0).getHeadImageUrl());
      if (StatusType.ON.equals(servicePerPersonalDTOList.get(0).getStatus())) {
        courtManageTokenUserDTO.setStatusIsBanned(false);
      } else {
        courtManageTokenUserDTO.setStatusIsBanned(true);
      }
      rowList.add(SecurityConst.ROLE_JUDGE_M);

      courtManageTokenUserDTO.setTypeList(rowList);
      return courtManageTokenUserDTO;
    }
    return null;
  }

  public boolean assistantUserIsExit(String userId, String phone, String password) {
    List<Assistanter> assistanterList = assistanterMapper
        .queryAssistantUsrByPhoneAndPassWord(userId, phone, password);
    if (null != assistanterList && assistanterList.size() == 1) {
      return false;
    }
    return true;
  }

  public Assistanter selectAssistanterById(String userId) {
    if (null == userId) {
      return null;
    }
    List<Assistanter> assistanterList = assistanterMapper
        .queryAssistantUsrByPhoneAndPassWord(userId, null, null);
    if (null != assistanterList && assistanterList.size() == 1) {
      return assistanterList.get(0);
    }
    return null;
  }

  public boolean assistanterChangePwdByUserId(String userId, String password, String phone) {
    int effectRow = 0;
    if (null != userId) {
      effectRow = assistanterMapper.updatePasswordByUserId(Long.parseLong(userId), password);
    }
    if (null != phone) {
      effectRow = assistanterMapper.updatePasswordByPhone(phone, password);
    }
    if (effectRow == 1) {
      return true;
    }
    return false;
  }

  public Admintor selectAdmintorById(String userId) {
    if (null == userId) {
      return null;
    }
    return admintorMapper.queryAdmintorById(Long.parseLong(userId));
  }

  public ServicePer selectCountManageByID(String userId) {
    if (null == userId) {
      return null;
    }
    return servicePerMapper.getServicePerById(Long.parseLong(userId));
  }
}
