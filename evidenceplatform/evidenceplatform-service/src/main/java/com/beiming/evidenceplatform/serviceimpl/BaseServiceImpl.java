package com.beiming.evidenceplatform.serviceimpl;

import com.beiming.evidenceplatform.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import java.util.List;
import javax.sql.DataSource;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

public class BaseServiceImpl<T> implements BaseService<T> {

  @Autowired
  protected Mapper<T> mapper;

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  public int insert(T entity) {
    return mapper.insert(entity);
  }

  @Override
  public int insertSelective(T entity) {
    return mapper.insertSelective(entity);
  }

  @Override
  public int deleteById(Long id) {
    return mapper.deleteByPrimaryKey(id);
  }

  @Override
  public int delete(T entity) {
    return mapper.delete(entity);
  }

  @Override
  public int update(T entity) {
    return mapper.updateByPrimaryKey(entity);
  }

  @Override
  public int updateSelective(T entity) {
    return mapper.updateByPrimaryKeySelective(entity);
  }

  @Override
  public List<T> select(T entity) {
    return mapper.select(entity);
  }

  @Override
  public T selectById(Long id) {
    return mapper.selectByPrimaryKey(id);
  }

  @Override
  public T selectOne(T entity) {
    return mapper.selectOne(entity);
  }

  @Override
  public T selectLastOne(T entity) {
    List<T> list = mapper.select(entity);
    if (list == null || list.size() == 0) {
      return null;
    } else {
      return list.get(list.size() - 1);
    }
  }

  @Override
  public int selectCount(T entity) {
    return mapper.selectCount(entity);
  }

  @Override
  public List<T> selectPage(int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    return mapper.select(null);
  }

  @Override
  @SuppressWarnings({"rawtypes", "unchecked"})
  public List<T> selectPage(T entity, int pageNum, int pageSize) {
    List resultList = mapper
        .selectByExampleAndRowBounds(entity, new RowBounds((pageNum - 1) * pageSize, pageSize));
    return resultList;
  }

  @Override
  public List<T> selectByEntityPage(T entity, int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    return mapper.select(entity);
  }

  @Override
  public List<T> selectByExample(Example example) {
    return mapper.selectByExample(example);
  }

}

