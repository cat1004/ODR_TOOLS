package com.beiming.evidenceplatform.service;

import java.util.List;

import com.beiming.evidenceplatform.domain.KeyUse;

/**
 * @author yanlulu
 * @version 创建时间：2018年6月25日 下午4:56:06 标的物的钥匙使用详情
 */
public interface KeyUseService {

  public List<KeyUse> getKeyUseByCorporeHouseId(int corporeHouseId);
  
  public List<KeyUse> getKeyUseByKeyId(int keyId);

  /**
   * @Title: addKeyUsing @Description: 添加钥匙使用记录 @param keyUse @return void @throws
   */
  public int addKeyUsing(KeyUse keyUse);

  /**
   * @Title: getKeyUsesByCorporeHouseId @Description: 获取该房产下所有钥匙使用记录 @param valueOf @return @return
   *         List<KeyUse> @throws
   */
  public List<KeyUse> getKeyUsesByCorporeHouseId(Long corporeHouseId);

  /**
   * @Title: updateKeyUse @Description: 更新钥匙使用记录 @param keyUse @return void @throws
   */
  public int updateKeyUse(KeyUse keyUse);

  void returnKey(String keyUseId);
}
