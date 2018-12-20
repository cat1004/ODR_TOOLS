package com.beiming.evidenceplatform.serviceimpl;


import com.beiming.evidenceplatform.dao.RealConditionMapper;
import com.beiming.evidenceplatform.domain.PhotoFiles;
import com.beiming.evidenceplatform.domain.RealCondition;
import com.beiming.evidenceplatform.domin.dto.requestdto.RealConditionFeedbackInfoDTO;
import com.beiming.evidenceplatform.service.RealConditionServiceI;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RealConditionServiceImpl implements RealConditionServiceI {

  private static final Logger LOGGER = LoggerFactory.getLogger(RealConditionServiceImpl.class);

  @Resource
  private RealConditionMapper realConditionMapper;
  /**
   * Cos 地址
   */
  @Value("${tencent.cos.url}")
  private String cos;
  /**
   * 現場看樣結果反饋
   * 
   * @param corporeHouseId 房屋id
   * @return
   */
  @Override
  public List<RealConditionFeedbackInfoDTO> getFeedbackMessage(Long corporeHouseId) {
    LOGGER.info("收到的房屋id参数为" + corporeHouseId);
    List<RealConditionFeedbackInfoDTO> list = null;
    list = realConditionMapper.findByCorporeHoust(corporeHouseId);
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {
        List<PhotoFiles> photoList = list.get(i).getPhotoList();
        for (PhotoFiles files : photoList) {
          if (files.getUrl() != null) {
            files.setUrl(cos + "/" + files + files.getUrl());
          }
        }
      }
    }
    return list;
  }

  @Override
  public long saveRealCondition(RealCondition realCondition) {
    return realConditionMapper.saveRealCondition(realCondition);
  }

  @Override
  public List<RealCondition> getRealConditionByCorporeHouseId(long corporeHouseId) {
    return realConditionMapper.getRealConditionByCorporeHouseId(corporeHouseId);
  }

  @Override
  public void updateRealCondition(RealCondition realCondition) {
    realConditionMapper.updateByPrimaryKeySelective(realCondition);
  }
}
