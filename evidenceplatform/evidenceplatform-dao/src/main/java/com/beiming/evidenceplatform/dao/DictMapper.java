package com.beiming.evidenceplatform.dao;

import java.util.List;

import com.beiming.evidenceplatform.domain.Dict;

import tk.mybatis.mapper.common.Mapper;

/**
 * @Description: 地区操作类接口
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/6/26
 */
public interface DictMapper extends Mapper<Dict> {

  /**
   * 获取标的物类型字典项
   */

  List<Dict> getAllCorporeType();
  
  /**
   * 获取标的物状态
   */
  Dict getcorporestate(String code);

  List<Dict> getCorporeType();

  /**
   * 获取标的物状态字典项
   */
  List<Dict> getCorporeStatus();


}