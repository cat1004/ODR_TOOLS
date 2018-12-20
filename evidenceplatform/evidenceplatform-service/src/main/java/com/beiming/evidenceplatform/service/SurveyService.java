package com.beiming.evidenceplatform.service;

import java.util.Map;

import com.beiming.evidenceplatform.domain.Survey;

/**
 * @author yanlulu
 * @version 创建时间：2018年6月25日 下午5:16:59 标的物勘验情况详细信息
 */
public interface SurveyService {
  public Survey getSurveyById(int id);

  @SuppressWarnings("rawtypes")
  public Map getSurveyDetail(Long id);

  long addSurvey(Survey survey);

  void updateSurvey(Survey survey);
}
