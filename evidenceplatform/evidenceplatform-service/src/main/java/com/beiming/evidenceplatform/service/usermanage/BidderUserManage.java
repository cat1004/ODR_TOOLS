package com.beiming.evidenceplatform.service.usermanage;

import com.beiming.evidenceplatform.domain.User;
import com.beiming.evidenceplatform.domain.UserDetail;
import com.beiming.evidenceplatform.domain.dto.UserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.BidderUserDTO;

/**
 * Created by xiaoqiang on 2018/6/28.
 */
public interface BidderUserManage {
  BidderUserDTO bidderUserLogin(String phone, String passWord);

  boolean bidderUserRegister(String phone, String passWord);

  boolean bidderUserModifyPassWord(String usrId, String passWord, String phone);

  boolean isBidderExitByPhone(String userId);

  User selectBidderUserById(String userId);

  UserDTO selectBidderUserInfoById(String userId);

  boolean verified(String userId, String actualName, String idCard);

  boolean bidderUserModifyName(String usrId, String name);

  boolean bidderUserModifyEmail(String usrId, String email);

  public boolean bidderUserModifyPhone(String usrId, String phone);

  UserDetail findByuserId(Long userId);
}
