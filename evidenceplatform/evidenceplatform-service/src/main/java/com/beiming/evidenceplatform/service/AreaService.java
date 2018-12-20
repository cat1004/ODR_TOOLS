package com.beiming.evidenceplatform.service;

import java.util.List;
import java.util.Map;

import com.beiming.evidenceplatform.domain.Area;

/**
 * @Description: 地区Service接口类
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/6/26
 */
public interface AreaService {

  /**
   * 获取全部的省份地区列表
   */
  List<Area> getAllSnameList();

  /**
   * 获取地级市列表
   */
  List<Area> getAllPrefecturalCity(String parentId);

  /**
   * 获取全国省份以及省份下的城市列表
   */
  Map<String, List<Area>> getAreas();

  Area getArea(long id);

  List<Area> getLowerLevelArea(String parentId);
}
