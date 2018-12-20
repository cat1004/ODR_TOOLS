package com.beiming.evidenceplatform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.beiming.evidenceplatform.domain.KeyUse;

import tk.mybatis.mapper.common.Mapper;

public interface KeyUseMapper extends Mapper<KeyUse> {

  public List<KeyUse> getKeyUseByCorporeHouseId(@Param("corporeHouseId") int corporeHouseId);

  /**
   * @Title: getKeyUsesByCorporeHouseId @Description: @param corporeHouseId @return @return List
   *         <KeyUse> @throws
   */
  public List<KeyUse> getKeyUsesByCorporeHouseId(Long corporeHouseId);
  
  public List<KeyUse> getKeyUseByKeyId(@Param("keyId") int keyId);

  int getKeyNumByKeyId(long keyId);

  KeyUse getKeyUseById(long id);
}
