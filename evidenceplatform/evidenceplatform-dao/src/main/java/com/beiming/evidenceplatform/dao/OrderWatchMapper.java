package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.OrderWatch;
import com.beiming.evidenceplatform.domain.OrderWatchPersonnel;
import com.beiming.evidenceplatform.domain.dto.OrderWatchDTO;
import com.beiming.evidenceplatform.domain.dto.OrderWatchToCorprehouseDTO;

import java.util.Date;
import java.util.List;
import tk.mybatis.mapper.common.Mapper;

public interface OrderWatchMapper extends Mapper<OrderWatch> {

  public List<OrderWatchDTO> getOrderWatchDTOListByCorporeHouseId(int corporeHouseId);

  List<OrderWatchToCorprehouseDTO> getUserOrderWatch(String phone);

  void insertOrderWatch(OrderWatch orderWatch);

  List<OrderWatchPersonnel> getOrderWatchPersonnels(long corporeHouseId);

  List<OrderWatch> getOrderWatchs(long corporeHouseId);

  List<Date> getOrderTimes(long corporeHouseId);
}
