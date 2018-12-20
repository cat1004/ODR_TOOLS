package com.beiming.evidenceplatform.serviceimpl;

import com.beiming.evidenceplatform.dao.DictMapper;
import com.beiming.evidenceplatform.domain.Dict;
import com.beiming.evidenceplatform.service.DictService;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Description: 字典表Service实现类
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/6/26
 */
@Service
public class DictServiceImpl implements DictService {

  @Resource
  private DictMapper dictMapper;

  /**
   * 获取标的物类型列表
   */
  @Override
  public List<Dict> getCorporeType() {
    return dictMapper.getCorporeType();
  }

  /**
   * 获取标的物状态字典项
   */
  @Override
  public List<Dict> getCorporeStatus() {
    return dictMapper.getCorporeStatus();
  }

  @Override
  public Dict getcorporestate(String code) {
    // TODO Auto-generated method stub
    
    return dictMapper.getcorporestate(code);
  }

  @Override
  public List<Dict> getAllCorporeType() {
    // TODO Auto-generated method stub
    return null;
  }
}
