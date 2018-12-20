package com.beiming.evidenceplatform.domain.dto;

import java.util.Date;
import lombok.Data;

/**
 * 标的物列表展示信息
 *
 * @author chenpeng
 */
@Data
public class CorporeInfoDTO {

  private Long id;

  /**
   * 标的物编号
   */
  private String no;

  /**
   * 标的物cphId
   */
  private Long cphId;

  /**
   * 标的物名称
   */
  private String name;

  /**
   * 拍卖时间
   */
  private Date auctionTime;

  /**
   * 标的物起拍价格
   */
  private String auctionPrice;

  /**
   * 标的物预约人数
   */
  private Integer reservationNumber;

  /**
   * 拍卖链接
   */
  private String auctionUrl;

  /**
   * 拍卖品图片地址
   */
  private String photoUrl;

  /**
   * 拍卖状态
   */
  private String status;

  /**
   * 地区名称
   */
  private String areaName;

  /**
   * 机构名称
   */
  private String orgName;

  /**
   * 上架状态
   */
  private String groundingtype;

}
