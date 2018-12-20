package com.beiming.evidenceplatform.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.beiming.evidenceplatform.domain.Admintor;

import tk.mybatis.mapper.common.Mapper;

public interface AdmintorMapper extends Mapper<Admintor> {
  /**
   *
   * @description 通过用户名密码查询系统管理人员
   */
  List<Admintor> querySystemUsrByNameAndPassWord(@Param("phone") String phone,
      @Param("password") String password);
  /**
   * 
   * @description 通过id查询系统管理员
   */
  Admintor queryAdmintorById(@Param("id") long id);
}
