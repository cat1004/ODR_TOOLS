package com.beiming.evidenceplatform.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beiming.evidenceplatform.dao.UserMapper;
import com.beiming.evidenceplatform.domain.User;
import com.beiming.evidenceplatform.service.UserService;

/**
 * 
 * 项目名称：jud-service 类名称：UserServiceImpl 类描述： 创建人：SLL 创建时间：2018年6月27日 下午8:32:32
 * 
 * @version
 */
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  UserMapper userMapper;

  @Override
  public User selectuseranddetailinfo(Integer id) {
    // TODO Auto-generated method stub
    User u = userMapper.selectuseranddetailinfo(id);
    return u;
  }

}
