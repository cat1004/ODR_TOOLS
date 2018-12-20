package com.beiming.evidenceplatform.service;

import com.beiming.evidenceplatform.domain.CorporeHouse;
import com.beiming.evidenceplatform.domain.dto.CorporeHouseDTO;

/**
 * @author zhangfc
 * @ClassName: CorporeHouseService
 * @Description: TODO
 * @date 2018年6月27日 上午9:14:19
 */
public interface CorporeHouseService {
  /**
   * @Title: addCorporeHouse @Description: @param corporeHouse @return void @throws
   */
  CorporeHouse addCorporeHouse(CorporeHouseDTO corporeHouseDTO);
  
  public CorporeHouse getCorporeHouseByCorporeId(int corporeId);

  void updateCorporeHouse(CorporeHouse corporeHouse);

  Long getSurveyIdByCorporeHouseId(long corporeHouseId);

  String getCorporeName(long corporeHouseId);
}
