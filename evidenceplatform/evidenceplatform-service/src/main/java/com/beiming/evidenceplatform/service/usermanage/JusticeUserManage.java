package com.beiming.evidenceplatform.service.usermanage;

import com.beiming.evidenceplatform.domain.ServicePer;
import com.beiming.evidenceplatform.domin.dto.requestdto.JudgeLoginUserDTO;
/**
 * Created by xiaoqiang on 2018/6/28.
 */
public interface JusticeUserManage {

  JudgeLoginUserDTO justiceUserLogin(String name, String passWord);
  JudgeLoginUserDTO justiceUserDetail(String userId, String userRole);
  boolean justiceChangePwd(String userID, String newPwd);

  ServicePer getServicePerById(String userId);

  boolean justiceChangePwdByPhone(String phone, String password);

  boolean isServicePerExitByPhone(String phone);

}
