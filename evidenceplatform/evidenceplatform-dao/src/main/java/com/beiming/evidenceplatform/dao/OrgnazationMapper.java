package com.beiming.evidenceplatform.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.beiming.evidenceplatform.domain.Orgnazation;

import tk.mybatis.mapper.common.Mapper;

/**
 * 
 * @author zhaomx
 * @date 2018年7月4日
 * @description 机构DAO
 */
public interface OrgnazationMapper extends Mapper<Orgnazation> {

  /**
   * 查询所有法院
   */
  public List<Orgnazation> queryAllCourt();

  /**
   * 根据地区获取法院列表
   */
  public List<Orgnazation> getCourtByArea(String areaId);

  /**
   * 根据地区id/类型/关键字查询机构
   */
  public List<Orgnazation> getOrgnazationByAreaIdAndTypeAndName(@Param("areaId") String areaId,
      @Param("type") String type, @Param("search") String search);

  /**
   * 根据机构名称及类型查询机构
   */
  public Orgnazation queryOrgnazationByName(@Param("name") String name, @Param("type") String type);

  /**
   * 添加机构
   */
  public void addOrgnazation(Orgnazation orgnazation);

  /**
   * 通过id查询机构
   */
  public Orgnazation getOrgnazationById(@Param("id") long id);

  /**
   * 通过id删除一条机构
   */
  public void deleteOrgnazation(@Param("id") long id);

  /**
   * 修改机构名称
   */
  public void updateOrgnazationName(Orgnazation orgnazation);
}
