package com.beiming.evidenceplatform.service;


import com.beiming.evidenceplatform.domain.OrderWatch;
import com.beiming.evidenceplatform.domain.OrderWatchPersonnel;
import com.beiming.evidenceplatform.domain.dto.OrderWatchDTO;
import com.beiming.evidenceplatform.domain.dto.OrderWatchToCorprehouseDTO;

import java.util.Date;
import java.util.List;

/**
 * @author yanlulu
 * @version 创建时间：2018年6月26日 上午11:36:11 获取预约看样信息
 */
public interface OrderWatchService {
  public List<OrderWatchDTO> getOrderWatchDTOListByCorporeHouseId(int corporeHouseId);

  public String getOrderWatchDetail(int corporeId);

  List<OrderWatchToCorprehouseDTO> getUserOrderWatch(String phone);

  void addOrderWatch(OrderWatchDTO orderWatchDTO);

  void updateOrderWatch(OrderWatch orderWatch);
  
  //添加用户，我要预约，添加预约订单级联添加预约详情订单
  String insertOrderWatchToPersonnel(OrderWatch orderWatch, Integer userid);

  List<OrderWatchPersonnel> getOrderWatchPersonnels(long corporeHouseId);

  List<OrderWatch> getOrderWatchs(long corporeHouseId);

  //获取预约时间列表
  List<Date> getOrderTimes(long corporeHouseId);
}
