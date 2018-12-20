package com.beiming.evidenceplatform.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.beiming.evidenceplatform.dao.AreaMapper;
import com.beiming.evidenceplatform.dao.CorporeMapper;
import com.beiming.evidenceplatform.dao.OrgnazationMapper;
import com.beiming.evidenceplatform.domain.Area;
import com.beiming.evidenceplatform.domain.CourtListDTO;
import com.beiming.evidenceplatform.domain.Orgnazation;
import com.beiming.evidenceplatform.domain.dto.CourtHomeDTO;
import com.beiming.evidenceplatform.redis.enums.RedisKey;
import com.beiming.evidenceplatform.service.OrgnazationService;
import com.beiming.evidenceplatform.service.RedisService;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description: 组织机构Service实现类-目前仅有法院
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/6/27
 */
@Service
public class OrgnazationServiceImpl implements OrgnazationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrgnazationServiceImpl.class);

  @Resource
  private AreaMapper areaMapper;

  @Resource
  private OrgnazationMapper orgnazationMapper;

  @Resource
  private CorporeMapper corporeMapper;

  @Resource
  private RedisService redisService;

  @Override
  public List<Orgnazation> getAllCourt() {
    return orgnazationMapper.queryAllCourt();
  }

  /**
   * 根据地区获取法院列表
   */
  @Override
  public Map<String, List<CourtListDTO>> getCourtList() {

    Map<String, List<CourtListDTO>> provinceAreaCourtMap = null;

    String json = redisService.get(RedisKey.JUD_COURT_LIST_SHOW);
    provinceAreaCourtMap = JSON.parseObject(json, (Type) provinceAreaCourtMap);

    if (provinceAreaCourtMap != null && !provinceAreaCourtMap.isEmpty()) {
      LOGGER.info("查询Redis找到了:   " + provinceAreaCourtMap);
    } else {
      provinceAreaCourtMap = new LinkedHashMap<>();
      List<CourtListDTO> resultCourtList = new ArrayList<>();
      List<Area> areaList = areaMapper.getAllSnameList();
      sortSnameList(areaList);

      CourtListDTO courtListDto = null;
      List<Map<String, Object>> courtListMap = null;

      if (areaList != null && areaList.size() > 0) {
        for (Area area : areaList) {
          courtListDto = new CourtListDTO();

          courtListMap = new ArrayList<>();
          putProvinceCourt(area, courtListMap);

          String parentId = area.getId();
          List<Area> cityList = areaMapper.getAllPrefecturalCity(parentId);
          formatCourtHomeMap(cityList, courtListMap);

          courtListDto.setProvinceName(area.getSname());
          courtListDto.setProvinceNameCode(area.getNameCode());
          courtListDto.setCourtList(courtListMap);
          resultCourtList.add(courtListDto);
        }
      }

      if (!resultCourtList.isEmpty()) {
        provinceAreaCourtMap = groupByProvinceArea(provinceAreaCourtMap, resultCourtList);
        String jsonObj = JSON.toJSONString(provinceAreaCourtMap);
        redisService.set(RedisKey.JUD_COURT_LIST_SHOW, jsonObj, 5, TimeUnit.MINUTES);
        LOGGER.info("保存至Redis成功！！长度：     " + provinceAreaCourtMap.size());
      }
    }

    return provinceAreaCourtMap;
  }

  /**
   * 根据省份的首字母进行排序
   */
  private void sortSnameList(List<Area> areaList) {
    Collections.sort(areaList, new Comparator<Area>() {
      @Override
      public int compare(Area o1, Area o2) {
        if (o1.getNameCode().substring(0, 1)
            .compareToIgnoreCase(o2.getNameCode().substring(0, 1)) > 0) {
          return 1;
        }
        if (o1.getNameCode().substring(0, 1)
            .compareToIgnoreCase(o2.getNameCode().substring(0, 1)) == 1) {
          return 0;
        }
        return -1;
      }
    });
  }

  /**
   * 获取省份级别的法院
   */
  private void putProvinceCourt(Area area, List<Map<String, Object>> courtListMap) {

    List<Orgnazation> orgnazationSList = orgnazationMapper.getCourtByArea(area.getId());
    List<CourtHomeDTO> courtHomeDtoSList = new ArrayList<>();
    if (orgnazationSList != null && orgnazationSList.size() > 0) {
      LOGGER.info("省级法院个数:  " + orgnazationSList.size());
      addCourtMethod(orgnazationSList, courtHomeDtoSList);
    }

    Map<String, Object> courtSMap = new LinkedHashMap<>();
    courtSMap.put("cityName", area.getSname());
    courtSMap.put("courtList", courtHomeDtoSList);

    courtListMap.add(courtSMap);
  }

  /**
   * 递归查询城市下的法院和法院标的物数量
   */
  private void formatCourtHomeMap(List<Area> cityList, List<Map<String, Object>> courtListMap) {
    if (cityList != null && cityList.size() > 0) {
      for (Area cityArea : cityList) {
        Map<String, Object> courtMap = new LinkedHashMap<>();

        courtMap.put("cityName", cityArea.getSname());
        List<Orgnazation> orgnazationList = orgnazationMapper.getCourtByArea(cityArea.getId());
        List<CourtHomeDTO> courtHomeDtoList = new ArrayList<>();

        if (orgnazationList != null && orgnazationList.size() > 0) {
          addCourtMethod(orgnazationList, courtHomeDtoList);
        }
        courtMap.put("courtList", courtHomeDtoList);
        courtListMap.add(courtMap);
      }
    } else {
      LOGGER.info("地级城市列表为空！！");
    }
  }

  /**
   * 抽出的循环添加法院的方法
   */
  private void addCourtMethod(List<Orgnazation> orgnazationList,
      List<CourtHomeDTO> courtHomeDtoList) {
    for (int i = 0; i < orgnazationList.size(); i++) {
      CourtHomeDTO courtHomeDto = new CourtHomeDTO();
      Long organizationId = orgnazationList.get(i).getId();
      int count = corporeMapper.getCorporeCountOfCourt(organizationId);
      courtHomeDto.setOrgId(Long.parseLong(orgnazationList.get(i).getId().toString()));
      courtHomeDto.setCoutrName(orgnazationList.get(i).getOrganizationName());
      courtHomeDto.setCorporeCountOfCourt(count);
      courtHomeDtoList.add(courtHomeDto);
    }
  }

  /**
   * 给省份地区加入首字母分组
   */
  private Map<String, List<CourtListDTO>> groupByProvinceArea(Map<String, List<CourtListDTO>> map,
      List<CourtListDTO> resultCourtList) {

    for (CourtListDTO courtListDto : resultCourtList) {

      /**
       * 截取省份首字母
       */
      String provinceKey = courtListDto.getProvinceNameCode().substring(0, 1);
      if (!map.containsKey(provinceKey)) {
        List<CourtListDTO> courtListDtoList = new ArrayList<>();
        courtListDtoList.add(courtListDto);
        map.put(provinceKey, courtListDtoList);
      } else {
        List<CourtListDTO> courtListDtoList = map.get(provinceKey);
        courtListDtoList.add(courtListDto);
      }

    }

    return map;
  }

  @Override
  public List<Orgnazation> getOrgnazationByAreaIdAndTypeAndName(String areaId, String type,
      String search) {
    return orgnazationMapper.getOrgnazationByAreaIdAndTypeAndName(areaId, type, search);
  }

  /**
   * 根据名称查询机构
   */
  @Override
  public Orgnazation getOrgnazationByName(String name, String type) {
    return orgnazationMapper.queryOrgnazationByName(name, type);
  }

  @Override
  public void addOrgnazation(Orgnazation orgnazation) {
    orgnazationMapper.addOrgnazation(orgnazation);
  }

  @Override
  public Orgnazation getOrgnazationById(long id) {
    return orgnazationMapper.getOrgnazationById(id);
  }

  @Override
  public void deleteOrgnazation(long id) {
    orgnazationMapper.deleteOrgnazation(id);
  }

  @Override
  public void modifyOrgnazationName(Orgnazation orgnazation) {
    orgnazationMapper.updateOrgnazationName(orgnazation);
  }

  @Override
  public List<Orgnazation> getOrgByAreaId(String areaId) {
    return orgnazationMapper.getCourtByArea(areaId);
  }
}
