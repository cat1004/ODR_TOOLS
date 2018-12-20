package com.beiming.evidenceplatform.service;

import java.util.List;

import com.beiming.evidenceplatform.domain.Dict;

/**
 * @Description: 字典表Service接口类
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/6/26
 */
public interface DictService {

  /**
   * 获取标的物类型列表
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
