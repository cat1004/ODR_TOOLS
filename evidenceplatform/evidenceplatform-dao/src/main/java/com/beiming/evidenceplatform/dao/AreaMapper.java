package com.beiming.evidenceplatform.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.beiming.evidenceplatform.domain.Area;

import tk.mybatis.mapper.common.Mapper;

/**
 * @Description: 地区操作类接口
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/6/26
 */
public interface AreaMapper extends Mapper<Area> {

  /**
   * 获取全部的省份地区列表
   */
  List<Area> getAllSnameList();

  /**
   * 获取地级市列表
   */
  List<Area> getAllPrefecturalCity(String parentId);
  /**
   * 
   * @author zhaomx
   * @description 根据地区id查询
   * @date 2018年7月2日
   * @param id
   * @return
   */
  Area getArea(@Param("id") long id);

  List<Area> getLowerLevelArea(String parentId);
}
