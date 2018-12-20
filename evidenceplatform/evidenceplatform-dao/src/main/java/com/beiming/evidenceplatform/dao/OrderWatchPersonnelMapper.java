package com.beiming.evidenceplatform.dao;

import java.util.List;

import com.beiming.evidenceplatform.domain.OrderWatchPersonnel;

import tk.mybatis.mapper.common.Mapper;

public interface OrderWatchPersonnelMapper extends Mapper<OrderWatchPersonnel> {

  public List<OrderWatchPersonnel> getOrderWatchPerSonnelListByOrderWatchId(int orderWatchId);

  void insertOrderWatchPersonnel(OrderWatchPersonnel orderWatchPersonnel);

  List<OrderWatchPersonnel> getOrderWatchPerSonnelsByCorporeHouseId(Long corporeHouseId);

  List<OrderWatchPersonnel> getOrderWatchPerSonnels(long orderWatchId);
}
