package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.CorporeHouse;

import tk.mybatis.mapper.common.Mapper;

public interface CorporeHouseMapper extends Mapper<CorporeHouse> {

  CorporeHouse getCorporeHouseByCorporeId(int corporeId);

  long addCorporeHouse(CorporeHouse corporeHouse);

  long getSurveyIdByCorporeHouseId(long corporeHouseId);

  String getCorporeName(long corporeHouseId);
}

