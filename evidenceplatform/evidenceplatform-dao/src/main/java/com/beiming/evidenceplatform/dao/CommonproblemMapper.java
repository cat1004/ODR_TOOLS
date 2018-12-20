package com.beiming.evidenceplatform.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.beiming.evidenceplatform.domain.Commonproblem;

import tk.mybatis.mapper.common.Mapper;

/**
 * 
 * 项目名称：jud-dao 类名称：CommonproblemMapper 类描述： 创建人：SLL 创建时间：2018年6月27日 下午2:43:26
 * 
 * @version
 */

@Repository
public interface CommonproblemMapper extends Mapper<Commonproblem> {

  void deleteToPrimaryKey(Integer id);

  void insertcommonproblem(Commonproblem commonproblem);

  Commonproblem selectToPrimaryKey(Integer id);

  List<Commonproblem> ordercommonproblemlist();

  void updateToPrimaryKey(Commonproblem commonproblem);

  List<Commonproblem> selectcommonproblemlists(Map<String, Object> map);
}
