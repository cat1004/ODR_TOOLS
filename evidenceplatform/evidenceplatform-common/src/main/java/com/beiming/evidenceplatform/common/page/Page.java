package com.beiming.evidenceplatform.common.page;

import java.io.Serializable;

public class Page implements Serializable {
  @Override
  public String toString() {
    return "Page [totalRecords=" + totalRecords + ", pageNo=" + pageNo + ", pageSize=" + pageSize
        + ", totalPages=" + totalPages + "]";
  }

  // 总记录数
  private int totalRecords;
  // 当前页
  private int pageNo = 1;
  // 每页显示多少条,默认10条
  private int pageSize = 10;
  // 总页数
  private int totalPages;

  // 取得第一页
  public int getTopPageNo() {
    return 1;
  }

  // 取得上一页
  public int getPreviousPageNo() {
    if (pageNo <= 1) {
      return 1;
    }
    return pageNo - 1;
  }

  // 取得下一页
  public int getNextPageNo() {
    if (pageNo >= getTotalPages()) { // 如果当前也大于页码
      return getTotalPages() == 0 ? 1 : getTotalPages(); // 返回最后一页
    }
    return pageNo + 1;
  }

  // 取得最后一页
  public int getLastPageNo() {
    return getTotalPages() == 0 ? 1 : getTotalPages(); // 如果总页数为0返回1，反之返回总页数
  }

  // 取得总页数
  public int getTotalPages() {
    int total = (totalRecords + pageSize - 1) / pageSize;
    return total <= 0 ? 1 : total; // 计算出总页数
  }

  public int getTotalRecords() {
    return totalRecords;
  }

  public void setTotalRecords(int totalRecords) {
    this.totalRecords = totalRecords;
  }

  // public int getPageNo() {
  // if (pageNo > 0) {
  // pageNo = (pageNo - 1) * pageSize;
  // }
  // return pageNo;
  // }
  public int getPageNo() {
    if (pageNo <= 1) {
      return 1;
    }
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
