package com.beiming.evidenceplatform.service.base;

import java.util.List;
import tk.mybatis.mapper.entity.Example;

public interface BaseService<T> {

  /**
   * 新增
   * 保存一个实体，null的属性也会保存，不会使用数据库默认值
   *
   * @return 执行成功的条数
   */
  int insert(T entity);

  /**
   * 新增
   * 保存一个实体，null的属性不会保存，会使用数据库默认值
   *
   * @return 执行成功的条数
   */
  int insertSelective(T entity);

  /**
   * 更新
   * 根据主键更新实体，null值会被更新
   *
   * @param entity 要含有主键
   * @return 执行成功的条数
   */
  int update(T entity);

  /**
   * 更新 指定字段
   * 根据主键更新属性不为null的值
   *
   * @param entity 要含有主键
   * @return 执行成功的条数
   */
  int updateSelective(T entity);


  /**
   * 删除
   * 根据主键字段进行删除，方法参数必须包含完整的主键属性
   *
   * @param id 主键ID
   * @return 执行成功的条数
   */
  int deleteById(Long id);

  /**
   * 删除
   * 根据实体属性作为条件进行删除，查询条件使用等号
   *
   * @return 执行成功的条数
   */
  int delete(T entity);

  /**
   * 查询
   * 根据实体中的属性值进行查询，查询条件使用等号
   */
  List<T> select(T entity);

  /**
   * 通过主键查询
   * 方法参数必须包含完整的主键属性，查询条件使用等号
   */
  T selectById(Long id);

  /**
   * 查询一条结果
   * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
   */
  T selectOne(T entity);

  /**
   * 根据实体查询出一条记录,无论结果有多少条记录，取出最新一条
   */
  T selectLastOne(T entity);

  /**
   * 统计记录数
   * 根据实体中的属性查询总数，查询条件使用等号
   *
   * @return 查到的记录数
   */
  int selectCount(T entity);

  /**
   * 按页查询
   */
  List<T> selectPage(int pageNum, int pageSize);

  /**
   * 查询加页面参数
   *
   * @param pageNum 当前页码
   * @param pageSize 一次查询的记录数
   */
  List<T> selectPage(T entity, int pageNum, int pageSize);

  /**
   * 按实体查询
   *
   * @param pageNum 当前页码
   * @param pageSize 一次查询的记录数
   */
  List<T> selectByEntityPage(T entity, int pageNum, int pageSize);

  /**
   * 带条件查询
   */
  List<T> selectByExample(Example example);

}
