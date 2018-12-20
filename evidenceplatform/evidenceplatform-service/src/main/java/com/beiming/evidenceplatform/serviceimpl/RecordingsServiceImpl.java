package com.beiming.evidenceplatform.serviceimpl;

import com.beiming.evidenceplatform.common.utils.Java8DateUtil;
import com.beiming.evidenceplatform.dao.RecordingsMapper;
import com.beiming.evidenceplatform.domain.Recordings;
import com.beiming.evidenceplatform.domain.dto.RecordingsDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.RecordingsInfo;
import com.beiming.evidenceplatform.service.RecordingsService;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 作者
 * @version 创建时间：2018年6月26日 上午10:01:08 类说明
 */
@Service
public class RecordingsServiceImpl implements RecordingsService {
  private static final Logger LOGGER = LoggerFactory.getLogger(RealConditionServiceImpl.class);
  @Resource
  private RecordingsMapper recordingsMapper;

  @Override
  public List<Recordings> getRecordingsByCorporeHouseId(int corporeHouseId) {
    return recordingsMapper.getRecordingsByCorporeHouseId(corporeHouseId);
  }

  @Override
  public Recordings addRecording(RecordingsDTO recordingsDTO) {
    Recordings recordings = new Recordings();
    recordings.setAdviceName(recordingsDTO.getAdviceName());
    recordings.setAdvicePhone(recordingsDTO.getAdvicePhone());
    recordings.setAdviceProblem(recordingsDTO.getAdviceProblem());
    recordings.setCorporeHouseId(recordingsDTO.getCorporeHouseId());
    recordings.setCreateTime(new Date());
    recordings.setType(recordingsDTO.getType());
    recordings.setCreateUser(recordingsDTO.getCreateUser());
    recordings.setAdviceTime(Java8DateUtil.getDate(recordingsDTO.getAdviceTime()));
    recordingsMapper.addRecordings(recordings);
    return recordings;
  }

  @Override
  public int updateReording(Recordings recording) {
    return recordingsMapper.updateByPrimaryKeySelective(recording);
  }

  @Override
  public List<RecordingsInfo> getRecordingsUrlByHouseId(Long corporeHouseId) {
    LOGGER.info("收到的房屋id参数为" + corporeHouseId);
    List<RecordingsInfo> list = recordingsMapper.getRecordingsUrlByCorporeHouseId(corporeHouseId);
    if (list != null) {
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).getUrl() != null) {
          list.get(i).setUrl(cos + "/" + list.get(i).getUrl());
        }
      }
    }
    return list;
  }
  @Value("${tencent.cos.url}")
  private String cos;
}
