package com.beiming.evidenceplatform.service;

import java.util.List;
import java.util.Map;

import com.beiming.evidenceplatform.domain.CourtListDTO;
import com.beiming.evidenceplatform.domain.Orgnazation;

/**
 * @author zhaomx, fenghouzhi
 * @date 2018年7月4日
 * @description
 */
public interface OrgnazationService {

  /**
   * 获取全部法院
   */
  List<Orgnazation> getAllCourt();

  /**
   * 获取省市对应的法院列表
   */
  Map<String, List<CourtListDTO>> getCourtList();

  /**
   * 根据地区id/type/关键字查询机构
   */
  List<Orgnazation> getOrgnazationByAreaIdAndTypeAndName(String areaId, String type,
      String search);

  /**
   * 根据名称和type查询机构
   */
  Orgnazation getOrgnazationByName(String name, String type);

  /**
   * 添加机构
   */
  void addOrgnazation(Orgnazation orgnazation);

  /**
   * 根据id查询机构
   */
  Orgnazation getOrgnazationById(long id);

  /**
   * 删除法院
   */
  void deleteOrgnazation(long id);

  /**
   * 修改法院
   */
  void modifyOrgnazationName(Orgnazation orgnazation);

  /**
   *根据地区获取法院
   */
  List<Orgnazation> getOrgByAreaId(String areaId);
}
