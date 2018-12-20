package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bank_info")
public class BankInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 银行名字
   */
  @Column(name = "bank_name")
  private String bankName;

  /**
   * 银行使用状态1贷款2配资
   */
  @Column(name = "bank_usestate")
  private String bankUsestate;

  /**
   * 银行经理或贷款负责人
   */
  @Column(name = "bank_personmanager")
  private String bankPersonmanager;

  /**
   * 银行电话
   */
  @Column(name = "bank_phone")
  private String bankPhone;

  @Column(name = "auction_detail_id")
  private Long auctionDetailId;

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
   * 获取银行名字
   *
   * @return bank_name - 银行名字
   */
  public String getBankName() {
    return bankName;
  }

  /**
   * 设置银行名字
   *
   * @param bankName 银行名字
   */
  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  /**
   * 获取银行使用状态1贷款2配资3
   *
   * @return bank_usestate - 银行使用状态1贷款2配资3
   */
  public String getBankUsestate() {
    return bankUsestate;
  }

  /**
   * 设置银行使用状态1贷款2配资3
   *
   * @param bankUsestate 银行使用状态1贷款2配资3
   */
  public void setBankUsestate(String bankUsestate) {
    this.bankUsestate = bankUsestate;
  }

  /**
   * 获取银行经理或贷款负责人
   *
   * @return bank_personmanager - 银行经理或贷款负责人
   */
  public String getBankPersonmanager() {
    return bankPersonmanager;
  }

  /**
   * 设置银行经理或贷款负责人
   *
   * @param bankPersonmanager 银行经理或贷款负责人
   */
  public void setBankPersonmanager(String bankPersonmanager) {
    this.bankPersonmanager = bankPersonmanager;
  }

  /**
   * 获取银行电话
   *
   * @return bank_phone - 银行电话
   */
  public String getBankPhone() {
    return bankPhone;
  }

  /**
   * 设置银行电话
   *
   * @param bankPhone 银行电话
   */
  public void setBankPhone(String bankPhone) {
    this.bankPhone = bankPhone;
  }

  /**
   * @return auction_detail_id
   */
  public Long getAuctionDetailId() {
    return auctionDetailId;
  }

  /**
   * @param auctionDetailId
   */
  public void setAuctionDetailId(Long auctionDetailId) {
    this.auctionDetailId = auctionDetailId;
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