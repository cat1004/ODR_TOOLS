package com.beiming.evidenceplatform.service;

import com.beiming.evidenceplatform.domain.User;

/**
 * 
 * 项目名称：jud-service 类名称：UserService 类描述： 创建人：SLL 创建时间：2018年6月27日 下午8:31:30
 * 
 * @version
 */
public interface UserService {
  User selectuseranddetailinfo(Integer id);
}
