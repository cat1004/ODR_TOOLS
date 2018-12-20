package com.beiming.evidenceplatform.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.beiming.evidenceplatform.domain.DeliveryPd;
import com.beiming.evidenceplatform.domin.dto.requestdto.DeliverypdInfo;

import tk.mybatis.mapper.common.Mapper;

public interface DeliveryPdMapper extends Mapper<DeliveryPd> {

  List<DeliverypdInfo> showDeliverpdReport(@Param("corporeHouseId") Long corporeHouseId);

}
