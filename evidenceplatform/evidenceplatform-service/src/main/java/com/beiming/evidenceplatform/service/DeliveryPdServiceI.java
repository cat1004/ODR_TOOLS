package com.beiming.evidenceplatform.service;

import java.util.List;

import com.beiming.evidenceplatform.domain.DeliveryPd;
import com.beiming.evidenceplatform.domin.dto.requestdto.DeliverypdInfo;

/**
 * 交割手续
 * 
 */
public interface DeliveryPdServiceI {
  List<DeliverypdInfo> getDeliverPdInfo(Long corporeHouseId);

  void saveDelivery(DeliveryPd deliveryPd);
}
