package com.beiming.evidenceplatform.service.usermanage;

import com.beiming.evidenceplatform.domain.Admintor;
import com.beiming.evidenceplatform.domain.Assistanter;
import com.beiming.evidenceplatform.domain.ServicePer;
import com.beiming.evidenceplatform.domin.dto.requestdto.AssistantTokenUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.CourtManageTokenUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.SystemTokenUserDTO;

/**
 * Created by xiaoqiang on 2018/6/28.
 */
public interface AssitantUserManage {

  AssistantTokenUserDTO assitantUserLogin(String phone, String passWord);

  SystemTokenUserDTO systemUserLogin(String name, String passWord);

  CourtManageTokenUserDTO courtManageUserLogin(String phone, String passWord);

  boolean assistantUserIsExit(String userId, String phone, String password);

  Assistanter selectAssistanterById(String userId);

  boolean assistanterChangePwdByUserId(String userId, String password, String phone);

  Admintor selectAdmintorById(String userId);

  ServicePer selectCountManageByID(String userId);

}
