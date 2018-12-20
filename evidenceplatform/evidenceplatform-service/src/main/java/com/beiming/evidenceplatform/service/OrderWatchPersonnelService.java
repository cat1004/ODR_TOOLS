package com.beiming.evidenceplatform.service;

import java.util.List;

import com.beiming.evidenceplatform.domain.OrderWatchPersonnel;

/**
 * @author yanlulu
 * @version 创建时间：2018年6月26日 下午1:12:35 预约看样人员详情
 */
public interface OrderWatchPersonnelService {
  public List<OrderWatchPersonnel> getOrderWatchPerSonnelListByOrderWatchId(int orderWatchId);


  void insertOrderWatchPersonnel(OrderWatchPersonnel orderWatchPersonnel);


  void addOrderWatchPersonnel(OrderWatchPersonnel orderWatchPersonnel);

  void updateOrderWatchPersonnel(List<OrderWatchPersonnel> orderWatchPersonnels);

  void deleteOrderPerson(Long id);

  void signOrderWatch(OrderWatchPersonnel orderWatchPersonnel);

  List<OrderWatchPersonnel> getOrderWatchPersonnelsByOrderWatch(long orderWatchId);
}
