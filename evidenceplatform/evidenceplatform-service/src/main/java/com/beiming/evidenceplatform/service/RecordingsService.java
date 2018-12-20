package com.beiming.evidenceplatform.service;

import com.beiming.evidenceplatform.domain.Recordings;
import com.beiming.evidenceplatform.domain.dto.RecordingsDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.RecordingsInfo;

import java.util.List;

/**
 * @author yanlulu
 * @version 创建时间：2018年6月26日 上午9:58:49 录音详情
 */
public interface RecordingsService {

  public List<Recordings> getRecordingsByCorporeHouseId(int corporeHouseId);

  Recordings addRecording(RecordingsDTO recordingsDTO);

  public int updateReording(Recordings recording);
  
  public List<RecordingsInfo> getRecordingsUrlByHouseId(Long corporeHouseId);
}
