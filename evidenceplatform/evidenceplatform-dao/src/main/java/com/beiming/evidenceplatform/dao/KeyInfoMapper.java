package com.beiming.evidenceplatform.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.beiming.evidenceplatform.domain.KeyInfo;

import tk.mybatis.mapper.common.Mapper;

public interface KeyInfoMapper extends Mapper<KeyInfo> {

  List<KeyInfo> getKeyByCorporeHouseId(@Param("corporeHouseId") int corporeHouseId);

  /**
   * @Title: getKeysByCorporeHouseId @Description: 获取房产下所有的钥匙 @param corporeHouseId @return @return
   * List<Key> @throws
   */
  List<KeyInfo> getKeysByCorporeHouseId(Long corporeHouseId);

  /**
   * @Title: getStockById
   * @Description:
   * @return String
   */
  String getStockById(Map<String, Object> map);

  /**
   * @Title: getKeyById
   * @Description:
   * @return Key
   */
  KeyInfo getKeyById(Long id);

  void addKeyInfo(KeyInfo keyInfo);
}
