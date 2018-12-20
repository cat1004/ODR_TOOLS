package com.beiming.evidenceplatform.service;

import java.util.List;
import java.util.Map;

import com.beiming.evidenceplatform.domain.Commonproblem;

/**
 * 
 * 项目名称：jud-service 类名称：CommonproblemService 类描述： 创建人：SLL 创建时间：2018年6月27日 下午3:06:01
 * 
 * @version
 */

public interface CommonproblemService {
  void deleteToPrimaryKey(Integer id);

  void insertcommonproblem(Commonproblem commonproblem);

  Commonproblem selectToPrimaryKey(Integer id);

  List<Commonproblem> ordercommonproblemlist();

  void updateToPrimaryKey(Commonproblem commonproblem);

  List<Commonproblem> selectcommonproblemlists(Map<String, Object> map);
}
