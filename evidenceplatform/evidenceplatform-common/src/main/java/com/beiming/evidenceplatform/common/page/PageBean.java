package com.beiming.evidenceplatform.common.page;

import java.util.ArrayList;
import java.util.List;

public class PageBean {


  private int currentPage = 1; // 当前页

  private int totalPage; // 总页数

  private int totalCount; // 总记录

  private int pageSize = 5; // 页面大小

  private Integer pagego = 0; // 每页记录

  private List<Integer> pages = new ArrayList<Integer>(); // 记录总页数用

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) { // 判断当传进来的当前页小于零。给个默认值为1，
                                                // 从第一页开始，如果当前页大于总页数则当前页指到最后一页
                                                // 正常情况下就把传进来的值，赋值给当前页
    if (currentPage <= 0) {
      this.currentPage = 1;
      this.pagego = (currentPage - 1) * this.pageSize;

    } else if (currentPage > this.getTotalPage()) {

      this.currentPage = totalPage;
      this.pagego = (currentPage - 1) * this.pageSize;

    } else {
      this.currentPage = currentPage;
      this.pagego = (currentPage - 1) * this.pageSize;
    }
  }

  public int getTotalPage() {

    if (totalCount % pageSize == 0) { // 总记录模页面大小=0，即能整除就拿到总页数

      this.totalPage = totalCount / pageSize;

    } else { // 如果不能被整除就在除完之后+1，即得到当前页，省略之前通过数值函数转换

      this.totalPage = totalCount / pageSize + 1;
    }
    return totalPage;
  }

  public int getTotalCount() {
    System.out.println("一共有" + this.totalCount);
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public List<Integer> getPages() {
    return pages;
  }

  public void setPages(List<Integer> pages) {
    this.pages = pages;
  }

  public Integer getPagego() {
    return pagego;
  }

  public void setPagego(Integer pagego) {
    this.pagego = pagego;
  }



}
