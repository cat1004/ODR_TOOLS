package com.beiming.evidenceplatform.domin.dto.requestdto;

import java.io.Serializable;

import com.beiming.evidenceplatform.common.page.Page;

import lombok.Data;

/**
 * @Description: 获取标的物列表查询条件DTO+分页包装类
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/7
 */
@Data
public class CorporePageQueryDTO implements Serializable {

  private CorporeRequestQueryDTO corporeRequestQueryDTO;

  private Page page;

}
