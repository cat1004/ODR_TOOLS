package com.beiming.evidenceplatform.serviceimpl;

import com.beiming.evidenceplatform.dao.DeliveryPdMapper;
import com.beiming.evidenceplatform.domain.DeliveryPd;
import com.beiming.evidenceplatform.domin.dto.requestdto.DeliverypdInfo;
import com.beiming.evidenceplatform.service.DeliveryPdServiceI;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPdServiceImpl implements DeliveryPdServiceI {

  private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryPdServiceImpl.class);

  @Resource
  private DeliveryPdMapper deliveryPdMapper;

  /**
   * 查询交割手续进度 信息
   */
  @Override
  public List<DeliverypdInfo> getDeliverPdInfo(Long corporeHouseId) {
    LOGGER.info("传入的房屋id参数为" + corporeHouseId);
    return deliveryPdMapper.showDeliverpdReport(corporeHouseId);
  }

  @Override
  public void saveDelivery(DeliveryPd deliveryPd) {
    deliveryPdMapper.insertSelective(deliveryPd);
  }
}
