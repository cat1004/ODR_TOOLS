package com.beiming.evidenceplatform.service;

import java.util.List;

import com.beiming.evidenceplatform.domain.KeyInfo;

/**
 * @author yanlulu
 * @version 创建时间：2018年6月25日 下午4:11:18 标的物钥匙详情的操作
 */
public interface KeyService {

  List<KeyInfo> getKeyByCorporeHouseId(int corporeHouseId);

  /**
   * @Title: addKey @Description: @param keys @param valueOf @return void @throws
   */
  int addKey(List<KeyInfo> keys);

  /**
   * @Title: getKeysByCorporeHouseId @Description: 获取房产下所有的钥匙 @param valueOf @return @return List
   * <Key> @throws
   */
  List<KeyInfo> getKeysByCorporeHouseId(Long corporeHouseId);

  String getKeyDetail(int corporeId);

}
