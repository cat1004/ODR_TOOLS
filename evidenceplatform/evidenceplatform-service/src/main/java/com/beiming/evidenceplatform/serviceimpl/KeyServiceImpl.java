package com.beiming.evidenceplatform.serviceimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.beiming.evidenceplatform.dao.KeyInfoMapper;
import com.beiming.evidenceplatform.dao.KeyUseMapper;
import com.beiming.evidenceplatform.domain.KeyInfo;
import com.beiming.evidenceplatform.domain.KeyUse;
import com.beiming.evidenceplatform.service.KeyService;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author yanlulu
 * @version 创建时间：2018年6月25日 下午4:12:12 类说明
 */
@Service
public class KeyServiceImpl implements KeyService {

  @Resource
  private KeyInfoMapper keyInfoMapper;
  
  @Resource
  private KeyUseMapper keyUseMapper;

  @Override
  public List<KeyInfo> getKeyByCorporeHouseId(int corporeHouseId) {
    return keyInfoMapper.getKeyByCorporeHouseId(corporeHouseId);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.beiming.jud.service.KeyService#addKey(java.util.List, java.lang.Long)
   */
  @Override
  public int addKey(List<KeyInfo> keys) {
    for (KeyInfo key : keys) {
      List<KeyInfo> keysResult = keyInfoMapper.getKeysByCorporeHouseId(key.getCorporeHouseId());
      if (keysResult == null) {
        key.setStock(key.getKeyNum().toString());
        key.setCreateTime(new Date());
        keyInfoMapper.insertSelective(key);
      }
      for (KeyInfo key1 : keysResult) {
        if (key1.getKeyName().equals(key.getKeyName())) {
          key.setUpdateTime(new Date());
          int stock1 = key.getKeyNum() - key1.getKeyNum() + Integer.parseInt(key1.getStock());
          if (stock1 < 0) {
            return -1;
          }
          String stock = String.valueOf(stock1);
          key.setStock(stock);
          key.setUpdateUser(key.getCreateUser());
          keyInfoMapper.updateByPrimaryKeySelective(key);
        }
        key.setStock(key.getKeyNum().toString());
        key.setCreateTime(new Date());
        keyInfoMapper.insertSelective(key);
      }
    }
    return 1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.beiming.jud.service.KeyService#getKeysByCorporeHouseId(java.lang.Long)
   */
  @Override
  public List<KeyInfo> getKeysByCorporeHouseId(Long corporeHouseId) {
    return keyInfoMapper.getKeysByCorporeHouseId(corporeHouseId);
  }

  @Override
  public String getKeyDetail(int corporeId) {
    List<KeyInfo> listk = keyInfoMapper.getKeyByCorporeHouseId(corporeId);
    JSONArray result = new JSONArray();
    if (listk.size() > 0) {
      for (KeyInfo k : listk) {
        JSONObject jo = new JSONObject();
        jo.put("key", k);
        List<KeyUse> listku =
            keyUseMapper.getKeyUseByKeyId(Integer.parseInt(String.valueOf(k.getId())));
        jo.put("keyUseList", listku);
        result.add(jo);
      }
    }
    return JSONArray.toJSONString(result, SerializerFeature.WriteMapNullValue);
  }

}
