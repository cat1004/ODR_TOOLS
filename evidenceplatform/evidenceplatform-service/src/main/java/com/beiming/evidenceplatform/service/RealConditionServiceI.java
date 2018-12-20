package com.beiming.evidenceplatform.service;

import com.beiming.evidenceplatform.domain.RealCondition;
import com.beiming.evidenceplatform.domin.dto.requestdto.RealConditionFeedbackInfoDTO;

import java.util.List;

public interface RealConditionServiceI {
  List<RealConditionFeedbackInfoDTO> getFeedbackMessage(Long corporeHouseId);

  long saveRealCondition(RealCondition realCondition);

  List<RealCondition> getRealConditionByCorporeHouseId(long corporeHouseId);

  void updateRealCondition(RealCondition realCondition);
}
