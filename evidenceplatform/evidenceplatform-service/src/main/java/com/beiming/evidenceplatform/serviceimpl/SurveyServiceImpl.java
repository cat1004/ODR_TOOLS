package com.beiming.evidenceplatform.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.beiming.evidenceplatform.dao.CorporeHouseMapper;
import com.beiming.evidenceplatform.dao.PhotoFilesMapper;
import com.beiming.evidenceplatform.dao.SurveyMapper;
import com.beiming.evidenceplatform.domain.CorporeHouse;
import com.beiming.evidenceplatform.domain.PhotoFiles;
import com.beiming.evidenceplatform.domain.Survey;
import com.beiming.evidenceplatform.service.SurveyService;

/**
 * @author yanlulu
 * @version 创建时间：2018年6月25日 下午5:17:57 类说明
 */
@Service
public class SurveyServiceImpl implements SurveyService {

  /**
   * Tencent Cos 地址
   */
  @Value("${tencent.cos.url}")
  private String cos;

  @Resource
  private SurveyMapper surveyMapper;

  @Resource
  private CorporeHouseMapper corporeHouseMapper;

  @Resource
  private PhotoFilesMapper photoFilesMapper;

  @Override
  public Survey getSurveyById(int id) {
    return surveyMapper.getSurveyById(id);
  }

  @Override
  public Map getSurveyDetail(Long id) {
    Map map = new HashMap(2);
    CorporeHouse ch = corporeHouseMapper.getCorporeHouseByCorporeId(Integer.parseInt(id + ""));
    Survey survey = surveyMapper.getSurveyById(Integer.parseInt(String.valueOf(ch.getSurveyId())));
    List<PhotoFiles> photoFileList = photoFilesMapper.getPhotosList(survey.getId(), "survey_id");

    addTencentCosToPhototUrl(photoFileList);

    map.put("survey", survey);
    map.put("photoFileList", photoFileList);
    return map;
  }

  @Override
  public long addSurvey(Survey survey) {
    return surveyMapper.addSurvey(survey);
  }

  @Override
  public void updateSurvey(Survey survey) {
    surveyMapper.updateByPrimaryKeySelective(survey);
  }

  /**
   * 拼接腾讯云COS前缀到图片路径上
   */
  private void addTencentCosToPhototUrl(List<PhotoFiles> photoFileList) {

    if (photoFileList != null && photoFileList.size() > 0) {
      for (PhotoFiles photoFile : photoFileList) {
        photoFile.setUrl(cos + "/" + photoFile.getUrl());
      }
    }

  }

}
