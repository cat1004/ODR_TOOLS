package com.beiming.evidenceplatform.serviceimpl;

import com.beiming.evidenceplatform.dao.AreaMapper;
import com.beiming.evidenceplatform.domain.Area;
import com.beiming.evidenceplatform.service.AreaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description: 地区Service实现类
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/2
 */
@Service
public class AreaServiceImpl implements AreaService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AreaServiceImpl.class);

  @Resource
  private AreaMapper areaMapper;

  @Override
  public List<Area> getAllSnameList() {
    return areaMapper.getAllSnameList();
  }

  @Override
  public List<Area> getAllPrefecturalCity(String parentId) {
    return areaMapper.getAllPrefecturalCity(parentId);
  }

  @Override
  public Map<String, List<Area>> getAreas() {
    Map<String, List<Area>> areaMap = new HashMap<>();
    List<Area> provinceList = getAllSnameList();
    for (int i = 0; i < provinceList.size(); i++) {
      String provinceId = provinceList.get(i).getId();
      String provinceName = provinceList.get(i).getSname();
      List<Area> cityList = getAllPrefecturalCity(provinceId);
      areaMap.put(provinceName, cityList);
    }
    return areaMap;
  }

  @Override
  public Area getArea(long id) {
    // TODO Auto-generated method stub
    return areaMapper.getArea(id);
  }

  @Override
  public List<Area> getLowerLevelArea(String parentId) {
    return areaMapper.getLowerLevelArea(parentId);
  }
}
