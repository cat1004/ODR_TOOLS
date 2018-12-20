package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.Personnel;

import tk.mybatis.mapper.common.Mapper;

public interface PersonnelMapper extends Mapper<Personnel> {
  public void addPersonnel(Personnel personnel);

  /**
   * 修改身份表状态
   */
  public int updatePersonnel(Personnel personnel);
}
