package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

public class Survey {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 勘验人员
   */
  private String name;

  /**
   * 水费
   */
  @Column(name = "water_charges")
  private String waterCharges;

  /**
   * 勘验完成时间
   */
  @Column(name = "finish_time")
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private Date finishTime;

  /**
   * 电费
   */
  @Column(name = "elec_charges")
  private String elecCharges;

  /**
   * 物业费
   */
  @Column(name = "property_fees")
  private String propertyFees;

  /**
   * 税费
   */
  private String taxes;

  /**
   * 租用情况
   */
  private String rent;

  /**
   * 占用情况
   */
  private String occupy;

  /**
   * 其他
   */
  private String other;

  /**
   * 创建时间
   */
  @Column(name = "create_time")
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  /**
   * 更新时间
   */
  @Column(name = "update_time")
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
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
   * 获取勘验人员
   *
   * @return name - 勘验人员
   */
  public String getName() {
    return name;
  }

  /**
   * 设置勘验人员
   *
   * @param name 勘验人员
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 获取水费
   *
   * @return water_charges - 水费
   */
  public String getWaterCharges() {
    return waterCharges;
  }

  /**
   * 设置水费
   *
   * @param waterCharges 水费
   */
  public void setWaterCharges(String waterCharges) {
    this.waterCharges = waterCharges;
  }

  /**
   * 获取勘验完成时间
   *
   * @return finish_time - 勘验完成时间
   */
  public Date getFinishTime() {
    return finishTime;
  }

  /**
   * 设置勘验完成时间
   *
   * @param finishTime 勘验完成时间
   */
  public void setFinishTime(Date finishTime) {
    this.finishTime = finishTime;
  }

  /**
   * 获取电费
   *
   * @return elec_charges - 电费
   */
  public String getElecCharges() {
    return elecCharges;
  }

  /**
   * 设置电费
   *
   * @param elecCharges 电费
   */
  public void setElecCharges(String elecCharges) {
    this.elecCharges = elecCharges;
  }

  /**
   * 获取物业费
   *
   * @return property_fees - 物业费
   */
  public String getPropertyFees() {
    return propertyFees;
  }

  /**
   * 设置物业费
   *
   * @param propertyFees 物业费
   */
  public void setPropertyFees(String propertyFees) {
    this.propertyFees = propertyFees;
  }

  /**
   * 获取税费
   *
   * @return taxes - 税费
   */
  public String getTaxes() {
    return taxes;
  }

  /**
   * 设置税费
   *
   * @param taxes 税费
   */
  public void setTaxes(String taxes) {
    this.taxes = taxes;
  }

  /**
   * 获取租用情况
   *
   * @return rent - 租用情况
   */
  public String getRent() {
    return rent;
  }

  /**
   * 设置租用情况
   *
   * @param rent 租用情况
   */
  public void setRent(String rent) {
    this.rent = rent;
  }

  /**
   * 获取占用情况
   *
   * @return occupy - 占用情况
   */
  public String getOccupy() {
    return occupy;
  }

  /**
   * 设置占用情况
   *
   * @param occupy 占用情况
   */
  public void setOccupy(String occupy) {
    this.occupy = occupy;
  }

  /**
   * 获取其他
   *
   * @return other - 其他
   */
  public String getOther() {
    return other;
  }

  /**
   * 设置其他
   *
   * @param other 其他
   */
  public void setOther(String other) {
    this.other = other;
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
