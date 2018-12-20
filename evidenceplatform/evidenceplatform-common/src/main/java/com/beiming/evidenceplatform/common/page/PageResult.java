package com.beiming.evidenceplatform.common.page;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 
 * @author qsh
 *
 * @param <T>
 */
@SuppressWarnings("serial")
@Data
public class PageResult<T> implements Serializable {

  // 返回列表
  private List<T> list;
  // 其他需要返回数据
  private Object object;

  @Override
  public String toString() {
    return "PageResult [list=" + list + ", page=" + page + "]";
  }

  // 分页
  private Page page;

}
