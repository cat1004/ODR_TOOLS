package com.beiming.evidenceplatform.serviceimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.beiming.evidenceplatform.common.utils.ValiDateUtils;
import com.beiming.evidenceplatform.dao.CorporeHouseMapper;
import com.beiming.evidenceplatform.dao.OrderWatchMapper;
import com.beiming.evidenceplatform.dao.OrderWatchPersonnelMapper;
import com.beiming.evidenceplatform.dao.UserMapper;
import com.beiming.evidenceplatform.domain.CorporeHouse;
import com.beiming.evidenceplatform.domain.OrderWatch;
import com.beiming.evidenceplatform.domain.OrderWatchPersonnel;
import com.beiming.evidenceplatform.domain.User;
import com.beiming.evidenceplatform.domain.dto.OrderWatchDTO;
import com.beiming.evidenceplatform.domain.dto.OrderWatchToCorprehouseDTO;
import com.beiming.evidenceplatform.service.OrderWatchService;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author yanlulu
 * @version 创建时间：2018年6月26日 上午11:37:33 类说明
 */
@Service
public class OrderWatchServiceImpl implements OrderWatchService {

  @Resource
  private OrderWatchMapper orderWatchMapper;

  @Resource
  private CorporeHouseMapper corporeHouseMapper;

  @Resource
  private OrderWatchPersonnelMapper orderWatchPersonnelMapper;

  @Autowired
  UserMapper userMapper;
  
  @Override
  public List<OrderWatchDTO> getOrderWatchDTOListByCorporeHouseId(int corporeHouseId) {
    return orderWatchMapper.getOrderWatchDTOListByCorporeHouseId(corporeHouseId);
  }

  @Override
  public String getOrderWatchDetail(int corporeId) {
    CorporeHouse corporeHouse = corporeHouseMapper.getCorporeHouseByCorporeId(corporeId);
    List<OrderWatchDTO> listOwd = orderWatchMapper.getOrderWatchDTOListByCorporeHouseId(
        Integer.parseInt(String.valueOf(corporeHouse.getId())));
    JSONArray ja = new JSONArray();
    for (OrderWatchDTO orderWatchDTO : listOwd) {
      JSONObject jo = new JSONObject();
      jo.put("orderWatchDTO", orderWatchDTO);
      List<OrderWatchPersonnel> listOwp =
          orderWatchPersonnelMapper.getOrderWatchPerSonnelListByOrderWatchId(orderWatchDTO.getId());
      jo.put("listOwp", listOwp);
      ja.add(jo);
    }
    return JSONArray.toJSONString(ja, SerializerFeature.WriteMapNullValue);
  }

  @Override
  public List<OrderWatchToCorprehouseDTO> getUserOrderWatch(String phone) {
    // TODO Auto-generated method stub
    List<OrderWatchToCorprehouseDTO> list = orderWatchMapper.getUserOrderWatch(phone);
    return list;
  }

  @Override
  public void addOrderWatch(OrderWatchDTO orderWatchDTO) {

    OrderWatch orderWatch = new OrderWatch();
    orderWatch.setCorporeHouseId(orderWatchDTO.getCorporeHouseId());
    orderWatch.setOrderTime(orderWatchDTO.getOrderTime());
    orderWatch.setOrderWatchPersonnellist(orderWatchDTO.getOrderWatchPersonnels());
    orderWatch.setCreateTime(new Date());
    orderWatchMapper.insertOrderWatch(orderWatch);
  }

  @Override
  public void updateOrderWatch(OrderWatch orderWatch) {
    orderWatch.setUpdateTime(new Date());
    orderWatchMapper.updateByPrimaryKeySelective(orderWatch);

  }



  /*
   * 
   * 通过事物进行控制，如果添加预约订单成功，才添加预约详情订单，否则全回滚
   *
   * @author SLL
   */
  @Override
  @Transactional
  public String insertOrderWatchToPersonnel(OrderWatch orderWatch, Integer userid) {
    // TODO Auto-generated method stub
    JSONObject j = new JSONObject();
    try {
      User user = userMapper.selectuseranddetailinfo(userid); // 记录登录人的ID
      if (user == null) {
        j.put("code", "400");
        j.put("msg", "用户获取失败，令牌有有误！");
        return j.toString();
      }

      if (!ValiDateUtils.validatename(orderWatch.getCreateUser())) {
        j.put("code", "400");
        j.put("msg", "请正确输入创建人名称，只能输入2到4个汉字！");
        return j.toString();
      }
      orderWatch.setId(null);
      orderWatch.setUpdateTime(null);
      orderWatch.setUpdateUser(null);

      orderWatchMapper.insertOrderWatch(orderWatch);

      OrderWatchPersonnel orderWatchPersonnel = new OrderWatchPersonnel();
      orderWatchPersonnel.setId(null);
      orderWatchPersonnel.setOrderName(user.getUserdetail().getActualName());
      orderWatchPersonnel.setOrderPhone(user.getUserdetail().getPhone());
      orderWatchPersonnel.setOrderWatchId(orderWatch.getId());
      orderWatchPersonnel.setIsCome("0");
      orderWatchPersonnel.setDelFlag("0");
      orderWatchPersonnel.setCreateTime(new Date());
      orderWatchPersonnel.setUpdateTime(null);
      orderWatchPersonnel.setCreateUser(user.getUserdetail().getActualName());
      orderWatchPersonnel.setUpdateUser(null);
      orderWatchPersonnel.setVersion(1);
      orderWatchPersonnel.setRemark("");

      orderWatchPersonnelMapper.insertOrderWatchPersonnel(orderWatchPersonnel);
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      j.put("code", "400");
      j.put("msg", "添加预约失败，事物回滚中！");
      return j.toString();
    }
    j.put("code", "200");
    j.put("msg", "预约成功，请届时及时签到！");
    return j.toString();


  }


  @Override
  public List<OrderWatchPersonnel> getOrderWatchPersonnels(long corporeHouseId) {
    return orderWatchMapper.getOrderWatchPersonnels(corporeHouseId);
  }

  @Override
  public List<OrderWatch> getOrderWatchs(long corporeHouseId) {

    return orderWatchMapper.getOrderWatchs(corporeHouseId);
  }

  @Override
  public List<Date> getOrderTimes(long corporeHouseId) {
    return orderWatchMapper.getOrderTimes(corporeHouseId);
  }
}
