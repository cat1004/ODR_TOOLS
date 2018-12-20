package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.RealCondition;
import com.beiming.evidenceplatform.domin.dto.requestdto.RealConditionFeedbackInfoDTO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

public interface RealConditionMapper extends Mapper<RealCondition> {

  List<RealConditionFeedbackInfoDTO> findByCorporeHoust(@Param("corporeHouseId") Long corporeHouseId);


  long saveRealCondition(RealCondition realCondition);

  List<RealCondition> getRealConditionByCorporeHouseId(long corporeHouseId);
}
