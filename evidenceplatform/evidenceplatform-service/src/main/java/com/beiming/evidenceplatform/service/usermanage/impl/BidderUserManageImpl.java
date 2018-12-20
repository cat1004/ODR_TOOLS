
package com.beiming.evidenceplatform.service.usermanage.impl;

import com.beiming.evidenceplatform.common.constants.SecurityConst;
import com.beiming.evidenceplatform.dao.EvidenceMapper;
import com.beiming.evidenceplatform.dao.MessageMapper;
import com.beiming.evidenceplatform.dao.UserDetailMapper;
import com.beiming.evidenceplatform.dao.UserMapper;
import com.beiming.evidenceplatform.domain.User;
import com.beiming.evidenceplatform.domain.UserDetail;
import com.beiming.evidenceplatform.domain.dto.UserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.BidderUserDTO;
import com.beiming.evidenceplatform.service.usermanage.BidderUserManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xiaoqiang on 2018/6/28.
 */

@Service
@Transactional
public class BidderUserManageImpl implements BidderUserManage {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserDetailMapper userDetailMapper;

  @Autowired
  private MessageMapper messageMapper;

  @Autowired
  private EvidenceMapper evidenceMapper;

  public BidderUserDTO bidderUserLogin(String phone, String passWord) {
    if (null == phone || null == passWord) {
      return null;
    }
    BidderUserDTO bidderUserDTO = new BidderUserDTO();
    List<BidderUserDTO> judgeLoginUserDTOList =
        userMapper.queryBidderUserByNameAndPassWord(phone, passWord);
    if (null != judgeLoginUserDTOList && judgeLoginUserDTOList.size() > 0) {

      List<String> rowList = new ArrayList<String>();
      bidderUserDTO.setUserId(judgeLoginUserDTOList.get(0).getUserId());
      bidderUserDTO.setActualName(judgeLoginUserDTOList.get(0).getActualName());
      bidderUserDTO.setSocialIdentify(judgeLoginUserDTOList.get(0).getSocialIdentify());
      bidderUserDTO.setHeadImageUrl(judgeLoginUserDTOList.get(0).getHeadImageUrl());
      rowList.add(SecurityConst.ROLE_USER);

      bidderUserDTO.setTypeList(rowList);
      return bidderUserDTO;
    }
    return null;
  }

  @Transactional
  public boolean bidderUserRegister(String phone, String password) {
    User user = new User();
    user.setPassword(password);
    user.setName(phone);
    int effectRowUser = userMapper.insertUser(user);
    if (effectRowUser == 0 || user == null) {
      return false;
    }
    long userId = user.getId();
    if (0L != userId) {
      userDetailMapper.insertUserDetailWithUserId(phone, userId);
      messageMapper.insertMessageWithUserId("尊敬的用户，您已成功注册账号" + phone + "。", userId);
      return true;
    }
    return false;
  }

  public boolean bidderUserModifyPassWord(String userId, String password, String phone) {
    int effectRow = 0;

    if (null == phone) {
      effectRow = userMapper.updateUserPassWordById(Long.parseLong(userId), password);
    } else if (null == userId) {
      effectRow = userMapper.updateUserPassWordByPhone(phone, password);
    } else {
      return false;
    }
    if (effectRow == 1) {
      return true;
    }
    return false;

  }

  public boolean verified(String userId, String actualName, String idCard) {
    int effectRow = 0;
    effectRow = userDetailMapper.verified(userId, actualName, idCard);
    if (effectRow == 1) {
      return true;
    }
    return false;
  }

  public boolean isBidderExitByPhone(String phone) {
    if (null == phone) {
      return true;
    }
    List<User> userList = userMapper.queryBidderUserByPhone(phone);
    if (null == userList || userList.size() == 0) {
      return false;
    }
    return true;
  }

  public User selectBidderUserById(String userId) {
    if (null == userId) {
      return null;
    }
    return userMapper.queryBidderUserById(Long.parseLong(userId));
  }

  public UserDTO selectBidderUserInfoById(String userId) {
    if (null == userId) {
      return null;
    }
    UserDTO bidderUser = userMapper.queryBidderUserInfoById(Long.parseLong(userId));
    bidderUser.setCapacity(Integer.parseInt(bidderUser.getCapacity()) / 1024 + "");
    if (bidderUser.getIdCard() != null && !bidderUser.getIdCard().trim().equals("")) {
      bidderUser.setStatus(1);
    }
    Integer evidenceNum = evidenceMapper.getEvidenceNumByUserId(Long.parseLong(userId));
    bidderUser.setEvidenceNum(evidenceNum);
    return bidderUser;
  }

  @Override
  public boolean bidderUserModifyName(String usrId, String name) {
    int effectRow = 0;
    effectRow = userMapper.updateUserNameById(usrId, name);
    if (effectRow == 2) {
      return true;
    }
    return false;
  }

  @Override
  public boolean bidderUserModifyEmail(String usrId, String email) {
    int effectRow = 0;
    effectRow = userMapper.updateUserEmailById(usrId, email);
    if (effectRow == 1) {
      return true;
    }
    return false;
  }

  @Override
  public boolean bidderUserModifyPhone(String usrId, String phone) {
    int effectRow = 0;
    effectRow = userDetailMapper.updatePhoneByUserId(usrId, phone);
    if (effectRow == 1) {
      return true;
    }
    return false;
  }

  @Override
  public UserDetail findByuserId(Long userId) {
    return userDetailMapper.findByuserId(userId);
  }

}

