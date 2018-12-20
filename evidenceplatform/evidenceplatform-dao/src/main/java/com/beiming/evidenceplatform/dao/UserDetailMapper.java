package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.UserDetail;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserDetailMapper extends Mapper<UserDetail> {
  int insertUserDetailWithUserId(@Param("phone") String phone, @Param("userId") long userId);

  int verified(@Param("userId") String userId, @Param("actualName") String actualName,
      @Param("idCard") String idCard);

  int updatePhoneByUserId(@Param("userId") String userId, @Param("phone") String phone);

  String getUsageAmountByUserId(@Param("userId") long userId);

  int updateUsageAmountByUserId(@Param("userId") String userId,
      @Param("usageAmount") String usageAmount);

  UserDetail findByuserId(@Param("userId") Long userId);
}
