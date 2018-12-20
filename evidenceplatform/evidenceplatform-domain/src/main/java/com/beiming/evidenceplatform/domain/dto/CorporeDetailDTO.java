package com.beiming.evidenceplatform.domain.dto;

import java.util.List;
import lombok.Data;

/**
 * @Description: 标的物详情实体类
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/2
 */
@Data
public class CorporeDetailDTO {

  /**
   * 标的物ID
   */
  private Long id;


  /**
   * 标的物cphID
   */
  private Long cphId;

  /**
   * 标的物adID
   */
  private Long adId;

  /**
   * 标的物编号
   */
  private String cpNo;

  /**
   * 标的物名称
   */
  private String name;

  /**
   * 楼层
   */
  private String floor;

  /**
   * 标的物起拍价格
   */
  private String auctionPrice;

  /**
   * 标的物拍卖周期
   */
  private String auctionPeriod;

  /**
   * 标的物保证金
   */
  private String cashDeposit;

  /**
   * 标的物拍卖加价幅度
   */
  private String auctionAddPrice;

  /**
   * 标的物保留价格
   */
  private String reservePrice;

  /**
   * 优先购买权
   */
  private String preemption;

  /**
   * 标的物起拍时间
   */
  private String auctionTime;

  /**
   * 土地性质
   */
  private String landNature;

  /**
   * 拍卖平台
   */
  private String auctionPlat;

  /**
   * 户型
   */
  private String houseType;

  /**
   * 年代
   */
  private String years;

  /**
   * 面积
   */
  private String acreage;

  /**
   * 房屋均价
   */
  private String hourseAveragePrice;

  /**
   * 用途
   */
  private String usering;

  /**
   * 标的物所在地区
   */
  private String areaName;

  /**
   * 标的物所属的组织机构
   */
  private String orgName;

  /**
   * 朝向（暂时数据库缺少该字段）
   */
  private String orientations;

  /**
   * 标的物图片集合
   */
  private List<String> photoUrlList;

  /**
   * 标的物预约人数
   */
  private Integer reservationNumber;

  /**
   * 标的物重要提示
   */
  private String content;

}
