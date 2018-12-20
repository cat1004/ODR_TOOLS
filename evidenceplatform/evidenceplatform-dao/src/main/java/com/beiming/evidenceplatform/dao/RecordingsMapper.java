package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.Recordings;
import com.beiming.evidenceplatform.domin.dto.requestdto.RecordingsInfo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

public interface RecordingsMapper extends Mapper<Recordings> {
  
  List<Recordings> getRecordingsByCorporeHouseId(int corporeHouseId);

  List<RecordingsInfo> getRecordingsUrlByCorporeHouseId(@Param("corporeHouseId") Long corporeHouseId);

  long addRecordings(Recordings recording);
  
}
