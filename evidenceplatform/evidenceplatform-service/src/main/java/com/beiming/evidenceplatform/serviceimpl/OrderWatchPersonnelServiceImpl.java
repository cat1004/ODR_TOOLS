package com.beiming.evidenceplatform.serviceimpl;

import com.beiming.evidenceplatform.dao.OrderWatchPersonnelMapper;
import com.beiming.evidenceplatform.domain.OrderWatchPersonnel;
import com.beiming.evidenceplatform.service.OrderWatchPersonnelService;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author 作者
 * @version 创建时间：2018年6月26日 下午1:13:34 类说明
 */
@Service
public class OrderWatchPersonnelServiceImpl implements OrderWatchPersonnelService {

  @Resource
  private OrderWatchPersonnelMapper orderWatchPersonnelMapper;

  @Override
  public List<OrderWatchPersonnel> getOrderWatchPerSonnelListByOrderWatchId(int orderWatchId) {
    return orderWatchPersonnelMapper.getOrderWatchPerSonnelListByOrderWatchId(orderWatchId);
  }

  @Override
  public void insertOrderWatchPersonnel(OrderWatchPersonnel orderWatchPersonnel) {
    // TODO Auto-generated method stub
    orderWatchPersonnelMapper.insertOrderWatchPersonnel(orderWatchPersonnel);
  }
  
  
  public void addOrderWatchPersonnel(OrderWatchPersonnel orderWatchPersonnel) {
    orderWatchPersonnelMapper.insertSelective(orderWatchPersonnel);

  }

  @Override
  public void updateOrderWatchPersonnel(List<OrderWatchPersonnel> orderWatchPersonnels) {
    for (OrderWatchPersonnel orderWatchPersonnel : orderWatchPersonnels) {
      orderWatchPersonnel.setUpdateTime(new Date());
      orderWatchPersonnelMapper.updateByPrimaryKeySelective(orderWatchPersonnel);
    }
  }

  @Override
  public void deleteOrderPerson(Long id) {
    OrderWatchPersonnel orderWatchPersonnel = new OrderWatchPersonnel();
    orderWatchPersonnel.setDelFlag("1");
    orderWatchPersonnel.setId(id);
    orderWatchPersonnel.setUpdateTime(new Date());
    orderWatchPersonnelMapper.updateByPrimaryKeySelective(orderWatchPersonnel);

  }

  @Override
  public void signOrderWatch(OrderWatchPersonnel orderWatchPersonnel) {
    orderWatchPersonnelMapper.updateByPrimaryKeySelective(orderWatchPersonnel);
  }

  @Override
  public List<OrderWatchPersonnel> getOrderWatchPersonnelsByOrderWatch(long orderWatchId) {
    return orderWatchPersonnelMapper.getOrderWatchPerSonnels(orderWatchId);
  }
}
