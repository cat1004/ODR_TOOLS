package com.beiming.evidenceplatform.serviceimpl;

import com.beiming.evidenceplatform.dao.KeyInfoMapper;
import com.beiming.evidenceplatform.dao.KeyUseMapper;
import com.beiming.evidenceplatform.domain.KeyInfo;
import com.beiming.evidenceplatform.domain.KeyUse;
import com.beiming.evidenceplatform.service.KeyUseService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author 作者
 * @version 创建时间：2018年6月25日 下午4:57:00 类说明
 */
@Service
public class KeyUseServiceImpl implements KeyUseService {

  @Resource
  private KeyUseMapper keyUseMapper;

  @Resource
  private KeyInfoMapper keyMapper;
  @Override
  public List<KeyUse> getKeyUseByCorporeHouseId(int corporeHouseId) {
    return keyUseMapper.getKeyUseByCorporeHouseId(corporeHouseId);
  }

  @Override
  public List<KeyUse> getKeyUseByKeyId(int keyId) {
    return keyUseMapper.getKeyUseByKeyId(keyId);
  }
  /*
   * (non-Javadoc)
   * 
   * @see com.beiming.jud.service.KeyUseService#addKeyUsing(com.beiming.jud.domain.KeyUse)
   */
  @Override
  public int addKeyUsing(KeyUse keyUse) {
    KeyInfo key = new KeyInfo();
    key.setId(keyUse.getKeyId());
    int usingNum = keyUse.getKeyNum();
    Map<String, Object> map = new HashMap<String, Object>();
    if (keyUse != null && keyUse.getKeyId() != 0) {

      map.put("id", keyUse.getKeyId());
      String stock1 = keyMapper.getStockById(map);

      if (Integer.parseInt(stock1) < usingNum) {
        return 1;
      }
      String stock = String.valueOf(Integer.parseInt(stock1) - usingNum);
      key.setStock(stock);
      key.setId(keyUse.getKeyId());

      keyMapper.updateByPrimaryKeySelective(key);

      keyUse.setCreateTime(new Date());
      keyUse.setUserTime(new Date());
      // 表示使用中
      keyUse.setStatus(1);

      keyUseMapper.insertSelective(keyUse);
      return 0;
    }
    return -1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.beiming.jud.service.KeyUseService#getKeyUsesByCorporeHouseId(java.lang.Long)
   */
  @Override
  public List<KeyUse> getKeyUsesByCorporeHouseId(Long corporeHouseId) {
    return keyUseMapper.getKeyUsesByCorporeHouseId(corporeHouseId);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.beiming.jud.service.KeyUseService#updateKeyUse(com.beiming.jud.domain.KeyUse)
   */
  @Override
  public int updateKeyUse(KeyUse keyUse) {
    KeyInfo key = new KeyInfo();
    key.setId(keyUse.getKeyId());
    int usingNum = keyUse.getKeyNum();
    Map<String, Object> map = new HashMap<String, Object>();
    if (keyUse != null && keyUse.getKeyId() != 0) {
      map.put("id", keyUse.getKeyId());
      String stock1 = keyMapper.getStockById(map);
      int keyNum = keyUseMapper.getKeyNumByKeyId(keyUse.getKeyId());
      int leftNum = keyNum - usingNum;
      if (Integer.parseInt(stock1) - leftNum < 0) {
        return 1;
      }
      String stock = String.valueOf(Integer.parseInt(stock1) + leftNum);

      key.setStock(stock);
      key.setId(keyUse.getKeyId());

      keyMapper.updateByPrimaryKeySelective(key);

      keyUse.setUpdateTime(new Date());
      // 表示使用中

      keyUseMapper.updateByPrimaryKeySelective(keyUse);
      return 0;
    }
    return -1;
  }

  @Override
  public void returnKey(String keyUseId) {
    KeyUse keyUse = keyUseMapper.getKeyUseById(Long.valueOf(keyUseId));
    Map<String, Object> map = new HashMap<>();
    map.put("id", keyUse.getKeyId());
    String stock1 = keyMapper.getStockById(map);
    int keyNum = keyUse.getKeyNum();
    stock1 = String.valueOf(Integer.parseInt(stock1) + keyNum);
    KeyInfo keyInfo = new KeyInfo();
    keyInfo.setStock(stock1);
    keyInfo.setId(keyUse.getKeyId());
    keyMapper.updateByPrimaryKeySelective(keyInfo);
    keyUse.setStatus(2);
    keyUse.setRealBackTime(new Date());
    keyUse.setUpdateTime(new Date());
    keyUseMapper.updateByPrimaryKey(keyUse);
  }
}
