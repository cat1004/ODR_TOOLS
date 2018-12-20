package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.User;
import com.beiming.evidenceplatform.domain.dto.UserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.BidderUserDTO;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {

  User selectuseranddetailinfo(Integer id);

  List<BidderUserDTO> queryBidderUserByNameAndPassWord(@Param("phone") String phone,
      @Param("passWord") String passWord);

  int insertUser(User user);

  int updateUserPassWordById(@Param("userId") long userId, @Param("password") String password);

  int updateUserPassWordByPhone(@Param("phone") String phone, @Param("password") String password);

  int updateUserNameById(@Param("userId") String userId, @Param("name") String name);

  int updateUserEmailById(@Param("userId") String userId, @Param("email") String email);

  List<User> queryBidderUserByPhone(@Param("phone") String phone);

  User queryBidderUserById(@Param("userId") long userId);

  UserDTO queryBidderUserInfoById(@Param("userId") long userId);
}
