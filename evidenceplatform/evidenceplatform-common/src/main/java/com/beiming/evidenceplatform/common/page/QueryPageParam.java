package com.beiming.evidenceplatform.common.page;

import java.io.Serializable;

/**
 * Created by xiet on 2017/4/19.
 *
 * 分页查询入参
 */
public class QueryPageParam implements Serializable {

  /**
   * 每页条数
   */
  private Integer pageSize;

  @Override
  public String toString() {
    return "QueryPageParam [pageSize=" + pageSize + ", pageNo=" + pageNo + ", sortField="
        + sortField + ", direction=" + direction + "]";
  }

  /**
   * 第几页
   */
  private Integer pageNo;

  /**
   * 排序字段
   */
  private String sortField;

  /**
   * 升序，降序
   */
  private String direction;

  public Integer getPageSize() {
    if (pageSize == null) {
      this.pageSize = 10;
    }
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getPageNo() {
    if (pageNo == null) {
      this.pageNo = 1;
    }
    return pageNo;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  public String getSortField() {
    return sortField;
  }

  public void setSortField(String sortField) {
    this.sortField = sortField;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }
}
