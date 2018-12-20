package com.beiming.evidenceplatform.serviceimpl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beiming.evidenceplatform.dao.CommonproblemMapper;
import com.beiming.evidenceplatform.domain.Commonproblem;
import com.beiming.evidenceplatform.service.CommonproblemService;

/**
 * 
 * 项目名称：jud-service 类名称：CommonproblemServiceImpl 类描述： 创建人：sll 创建时间：2018年6月27日 下午3:10:20
 * 
 * @version
 */
@Service
public class CommonproblemServiceImpl implements CommonproblemService {

  @Autowired
  CommonproblemMapper commonproblemMapper;

  @Override
  public void deleteToPrimaryKey(Integer id) {
    // TODO Auto-generated method stub
    commonproblemMapper.deleteByPrimaryKey(id);
  }

  @Override
  public void insertcommonproblem(Commonproblem commonproblem) {
    // TODO Auto-generated method stub
    commonproblemMapper.insertcommonproblem(commonproblem);

  }

  @Override
  public Commonproblem selectToPrimaryKey(Integer id) {
    // TODO Auto-generated method stub
    Commonproblem c = commonproblemMapper.selectByPrimaryKey(id);
    return c;
  }

  @Override
  public List<Commonproblem> ordercommonproblemlist() {
    // TODO Auto-generated method stub
    List<Commonproblem> list = commonproblemMapper.ordercommonproblemlist();
    return list;
  }

  @Override
  public void updateToPrimaryKey(Commonproblem commonproblem) {
    // TODO Auto-generated method stub
    commonproblemMapper.updateToPrimaryKey(commonproblem);
  }

  @Override
  public List<Commonproblem> selectcommonproblemlists(Map<String, Object> map) {
    // TODO Auto-generated method stub
    List<Commonproblem> list = commonproblemMapper.selectcommonproblemlists(map);
    return list;
  }

}
