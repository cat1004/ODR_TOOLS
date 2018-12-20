package com.beiming.evidenceplatform.service.usermanage.impl;

import com.beiming.evidenceplatform.common.constants.DictConst;
import com.beiming.evidenceplatform.common.constants.SecurityConst;
import com.beiming.evidenceplatform.common.constants.StatusType;
import com.beiming.evidenceplatform.dao.ServicePerMapper;
import com.beiming.evidenceplatform.domain.ServicePer;
import com.beiming.evidenceplatform.domin.dto.requestdto.JudgeLoginUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.ServicePerPersonalDTO;
import com.beiming.evidenceplatform.service.usermanage.JusticeUserManage;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaoqiang on 2018/6/28.
 */
@Service
@Slf4j
public class JusticeUserManageImpl implements JusticeUserManage {

  @Autowired
  private ServicePerMapper servicePerMapper;

  public JudgeLoginUserDTO justiceUserLogin(String phone, String passWord) {
    if (null == phone || null == passWord) {
      return null;
    }
    log.info("通过电话密码查询servicePer。\n手机号：{}\n密码：{}", phone, passWord);
    JudgeLoginUserDTO judgeLoginUserDTO = new JudgeLoginUserDTO();
    List<ServicePerPersonalDTO> servicePerPersonalDTOList = servicePerMapper
        .queryCourtUsrByPhoneAndPassWord(phone, passWord);
    if (null == servicePerPersonalDTOList || servicePerPersonalDTOList.size() == 0) {
      return null;
    }
    List<String> rowList = new ArrayList<String>();
    judgeLoginUserDTO.setUserId(servicePerPersonalDTOList.get(0).getUserId());
    judgeLoginUserDTO.setActualName(servicePerPersonalDTOList.get(0).getActualName());
    judgeLoginUserDTO.setSocialIdentify(servicePerPersonalDTOList.get(0).getSocialIdentify());
    judgeLoginUserDTO.setStatusIsBanned(true);
    judgeLoginUserDTO.setHeadImageUrl(servicePerPersonalDTOList.get(0).getHeadImageUrl());

    for (ServicePerPersonalDTO servicePerPersonalDTO : servicePerPersonalDTOList) {
      if (null != servicePerPersonalDTO && servicePerPersonalDTO.getRole()
          .equals(DictConst.JUDGE) && StatusType.ON.equals(servicePerPersonalDTO.getStatus())) {
        judgeLoginUserDTO.setStatusIsBanned(false);
        rowList.add(SecurityConst.ROLE_JUDGE);
      }
      if (null != servicePerPersonalDTO && servicePerPersonalDTO.getRole()
          .equals(DictConst.COURT_ADMINISTRATOR) && StatusType.ON
          .equals(servicePerPersonalDTO.getStatus())) {
        judgeLoginUserDTO.setStatusIsBanned(false);
        rowList.add(SecurityConst.ROLE_JUDGE_M);
      }
    }
    judgeLoginUserDTO.setTypeList(rowList);

    return judgeLoginUserDTO;
  }

  public JudgeLoginUserDTO justiceUserDetail(String userId, String userRole) {
    if (null == userId) {
      return null;
    }
    log.info("通过用户ID和角色查询servicePer。\n用户ID：{}\n用户角色：", userId, userRole);
    List<JudgeLoginUserDTO> judgeLoginUserDTOList = servicePerMapper
        .queryCourtUsrByUserId(userId, userRole);
    if (null != judgeLoginUserDTOList && judgeLoginUserDTOList.size() == 1) {
      return judgeLoginUserDTOList.get(0);
    }
    return null;
  }


  public boolean justiceChangePwd(String userId, String password) {
    if (null == userId || password == null) {
      return true;
    }
    ServicePer servicePer = new ServicePer();
    servicePer.setId(Long.parseLong(userId));
    servicePer.setPassword(password);
    log.info("通过用户ID更新用户密码。\n用户ID：{}\n用户新密码：", userId, password);
    int effectRow = servicePerMapper.updatePasswordById(servicePer);
    if (effectRow == 1) {
      log.info("通过修改用户密码成功");
      return false;
    } else if (effectRow > 1) {
      log.error("用户数据异常,多个相同id的用户密码被更新。\n更新用户id:{}", userId);
      return false;
    }
    return true;
  }

  public boolean justiceChangePwdByPhone(String phone, String password) {
    if (null == password || null == phone) {
      return false;
    }
    ServicePer servicePer = new ServicePer();
    servicePer.setPhone(phone);
    servicePer.setPassword(password);
    int effectRow = servicePerMapper.updatePasswordByPhone(servicePer);
    if (effectRow == 1) {
      return true;
    }
    return false;
  }

  public ServicePer getServicePerById(String userId) {
    if (null == userId) {
      return null;
    }
    return servicePerMapper.getServicePerById(Long.parseLong(userId));
  }

  public boolean isServicePerExitByPhone(String phone) {
    if (null == phone) {
      return false;
    }
    List<ServicePer> servicePerList = servicePerMapper.queryServicePersByPhoneNumber(phone);
    if (null != servicePerList && servicePerList.size() == 1) {
      return true;
    }
    return false;
  }


}
