package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.Survey;

import tk.mybatis.mapper.common.Mapper;

public interface SurveyMapper extends Mapper<Survey> {
  public Survey getSurveyById(int id);

  long addSurvey(Survey survey);
}
