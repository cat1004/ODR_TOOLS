/**
 * 
 */
package com.beiming.evidenceplatform.domain.dto;

import lombok.Data;

/**
 * @author zhangfc
 * @ClassName: CorporeHouseDTO
 * @Description: TODO
 * @date 2018年6月26日 上午10:53:27
 */
@Data
public class CorporeHouseDTO {
  private Long id; // 主键
  private Long surveyId; // 勘验id
  private String floor; // 楼层
  private Long assistanterId; // 辅助人员主键
  private Long corporeId; // 标的物主键
  private String usering; // usering
  private String years; // 年代
  private String acreage; // 面积
  private String houseType; // 户型
  private String landNature; // 土地性质
  private String orientations; //朝向
  private Long orgId; // 执行法院
  private Long servicePerId; // 法官
  private String url; //图片url
}
