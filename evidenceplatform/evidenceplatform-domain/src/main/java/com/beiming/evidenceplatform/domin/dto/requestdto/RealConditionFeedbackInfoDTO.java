package com.beiming.evidenceplatform.domin.dto.requestdto;

import java.util.Date;
import java.util.List;

import com.beiming.evidenceplatform.domain.PhotoFiles;

/**
 * 封装看样结果信息 包含看样反馈的图片地址,问题,时间
 * 
 * @author The King
 *
 */

@lombok.Data
public class RealConditionFeedbackInfoDTO {

  private Date orderTime; // 看样时间

  private String problems; // 看样问题

  private String url; // 看样图片地址

  private List<PhotoFiles> photoList; // 看样图片地址集合

}
