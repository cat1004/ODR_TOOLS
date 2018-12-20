package com.beiming.evidenceplatform.domain;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @Description: 首页统计数据DTO
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/6
 */
@Data
public class HomeDataDTO implements Serializable {

  /**
   * 成交总金额-标的物拍卖总金额（拍卖状态为已结束的）
   */
  private String transactionAmountSum;

  /**
   * 成交总金额-月环比（对比上月的金额，本月的增长情况）
   */
  private String monthOnMonthBasisForPrice;

  /**
   * 成交金额-平均溢价率（实际拍卖金额超过起拍金额的标的物的平均溢价率）
   */
  private String averagePremiumRate;

  /**
   * 成交标的物总量（拍卖状态为已结束的）
   */
  private String dealCorporeSum;

  /**
   * 成交率（成交标的物总量/委托标的物总量）
   */
  private String turnoverRate;

  /**
   * 成交标的物-月环比（对比上月的成交量，本月增长情况）
   */
  private String monthOnMonthBasisForVolume;

  /**
   * 一拍标的物数量
   */
  private String oneBeatCount;

  /**
   * 二拍标的物数量
   */
  private String secondBeatCount;

  /**
   * 变拍标的物数量
   */
  private String patCount;

  /**
   * 委托标的物总量（录入后台的所有标的物总量）
   */
  private String entrustmentSum;

  /**
   * 即将开拍物数量（拍卖状态为可预约、看样排期中的标的物总量）
   */
  private String soonStartSum;

  /**
   * 流拍标的物数量（拍卖状态为流拍的标的物）
   */
  private String racketSum;

  /**
   * 月同比（比去年该月的标的物总量，今年该月的增长情况）
   */
  private List<CorporeCountByMonth> monthOnYearForCount;

  /**
   * 月环比（对比上月标的物总量，本月的增长情况）
   */
  private String monthOnMonthBasisForCount;

  /**
   * 公示期标的物总量（已经增加拍卖链接且发布上架的标的物总量）
   */
  private String publicCorporeSum;

  /**
   * 月同比（比去年该月的标的物总量，今年该月的增长情况）
   */
  private List<CorporeCountByMonth> monthOnYearForPublic;

  /**
   * 月环比（对比上月标的物总量，本月的增长情况）
   */
  private String monthOnMonthBasisForPublic;

}
