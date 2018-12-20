package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "auction_detail")
public class AuctionDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "corpore_house_id")
  private Long corporeHouseId;

  /**
   * 拍卖链接
   */
  @Column(name = "auction_url")
  private String auctionUrl;

  /**
   * 拍卖平台
   */
  @Column(name = "auction_plat")
  private String auctionPlat;

  /**
   * 拍卖类型
   */
  @Column(name = "auction_type")
  private String auctionType;

  /**
   * 优先购买权
   */
  private String preemption;

  /**
   * 保留价
   */
  @Column(name = "reserve_price")
  private String reservePrice;

  /**
   * 保证金
   */
  @Column(name = "cash_deposit")
  private String cashDeposit;

  /**
   * 拍卖加幅
   */
  @Column(name = "auction_add_price")
  private String auctionAddPrice;

  /**
   * 起拍价格
   */
  @Column(name = "auction_price")
  private String auctionPrice;

  /**
   * 拍卖周期
   */
  @Column(name = "aucton_period")
  private String auctonPeriod;

  /**
   * 创建时间
   */
  @Column(name = "create_time")
  private Date createTime;

  /**
   * 更新时间
   */
  @Column(name = "update_time")
  private Date updateTime;

  /**
   * 创建人
   */
  @Column(name = "create_user")
  private String createUser;

  /**
   * 更新人
   */
  @Column(name = "update_user")
  private String updateUser;

  /**
   * 版本号
   */
  private Integer version;

  /**
   * 备注
   */
  private String remark;

  /**
   * @return id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return corpore_house_id
   */
  public Long getCorporeHouseId() {
    return corporeHouseId;
  }

  /**
   * @param corporeHouseId
   */
  public void setCorporeHouseId(Long corporeHouseId) {
    this.corporeHouseId = corporeHouseId;
  }

  /**
   * 获取拍卖链接
   *
   * @return auction_url - 拍卖链接
   */
  public String getAuctionUrl() {
    return auctionUrl;
  }

  /**
   * 设置拍卖链接
   *
   * @param auctionUrl 拍卖链接
   */
  public void setAuctionUrl(String auctionUrl) {
    this.auctionUrl = auctionUrl;
  }

  /**
   * 获取拍卖平台
   *
   * @return auction_plat - 拍卖平台
   */
  public String getAuctionPlat() {
    return auctionPlat;
  }

  /**
   * 设置拍卖平台
   *
   * @param auctionPlat 拍卖平台
   */
  public void setAuctionPlat(String auctionPlat) {
    this.auctionPlat = auctionPlat;
  }

  /**
   * 获取拍卖类型
   *
   * @return auction_type - 拍卖类型
   */
  public String getAuctionType() {
    return auctionType;
  }

  /**
   * 设置拍卖类型
   *
   * @param auctionType 拍卖类型
   */
  public void setAuctionType(String auctionType) {
    this.auctionType = auctionType;
  }

  /**
   * 获取优先购买权
   *
   * @return preemption - 优先购买权
   */
  public String getPreemption() {
    return preemption;
  }

  /**
   * 设置优先购买权
   *
   * @param preemption 优先购买权
   */
  public void setPreemption(String preemption) {
    this.preemption = preemption;
  }

  /**
   * 获取保留价
   *
   * @return reserve_price - 保留价
   */
  public String getReservePrice() {
    return reservePrice;
  }

  /**
   * 设置保留价
   *
   * @param reservePrice 保留价
   */
  public void setReservePrice(String reservePrice) {
    this.reservePrice = reservePrice;
  }

  /**
   * 获取保证金
   *
   * @return cash_deposit - 保证金
   */
  public String getCashDeposit() {
    return cashDeposit;
  }

  /**
   * 设置保证金
   *
   * @param cashDeposit 保证金
   */
  public void setCashDeposit(String cashDeposit) {
    this.cashDeposit = cashDeposit;
  }

  /**
   * 获取拍卖加幅
   *
   * @return auction_add_price - 拍卖加幅
   */
  public String getAuctionAddPrice() {
    return auctionAddPrice;
  }

  /**
   * 设置拍卖加幅
   *
   * @param auctionAddPrice 拍卖加幅
   */
  public void setAuctionAddPrice(String auctionAddPrice) {
    this.auctionAddPrice = auctionAddPrice;
  }

  /**
   * 获取起拍价格
   *
   * @return auction_price - 起拍价格
   */
  public String getAuctionPrice() {
    return auctionPrice;
  }

  /**
   * 设置起拍价格
   *
   * @param auctionPrice 起拍价格
   */
  public void setAuctionPrice(String auctionPrice) {
    this.auctionPrice = auctionPrice;
  }

  /**
   * 获取拍卖周期
   *
   * @return aucton_period - 拍卖周期
   */
  public String getAuctonPeriod() {
    return auctonPeriod;
  }

  /**
   * 设置拍卖周期
   *
   * @param auctonPeriod 拍卖周期
   */
  public void setAuctonPeriod(String auctonPeriod) {
    this.auctonPeriod = auctonPeriod;
  }

  /**
   * 获取创建时间
   *
   * @return create_time - 创建时间
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * 设置创建时间
   *
   * @param createTime 创建时间
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * 获取更新时间
   *
   * @return update_time - 更新时间
   */
  public Date getUpdateTime() {
    return updateTime;
  }

  /**
   * 设置更新时间
   *
   * @param updateTime 更新时间
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  /**
   * 获取创建人
   *
   * @return create_user - 创建人
   */
  public String getCreateUser() {
    return createUser;
  }

  /**
   * 设置创建人
   *
   * @param createUser 创建人
   */
  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  /**
   * 获取更新人
   *
   * @return update_user - 更新人
   */
  public String getUpdateUser() {
    return updateUser;
  }

  /**
   * 设置更新人
   *
   * @param updateUser 更新人
   */
  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
  }

  /**
   * 获取版本号
   *
   * @return version - 版本号
   */
  public Integer getVersion() {
    return version;
  }

  /**
   * 设置版本号
   *
   * @param version 版本号
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 获取备注
   *
   * @return remark - 备注
   */
  public String getRemark() {
    return remark;
  }

  /**
   * 设置备注
   *
   * @param remark 备注
   */
  public void setRemark(String remark) {
    this.remark = remark;
  }
}
