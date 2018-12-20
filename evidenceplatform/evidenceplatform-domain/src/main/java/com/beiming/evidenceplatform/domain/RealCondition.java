package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "real_condition")
public class RealCondition {
  /**
   * 预约时间
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "order_time")
  private Date orderTime;

  @Column(name = "corpore_house_id")
  private Long corporeHouseId;
  /**
   * 看样顾问
   */
  private String consultant;

  /**
   * 看样现场问题汇总
   */
  private String problems;

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
   * 获取预约时间
   *
   * @return id - 预约时间
   */
  public Long getId() {
    return id;
  }

  /**
   * 设置预约时间
   *
   * @param id 预约时间
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return order_time
   */
  public Date getOrderTime() {
    return orderTime;
  }

  /**
   * @param orderTime
   */
  public void setOrderTime(Date orderTime) {
    this.orderTime = orderTime;
  }

  /**
   * 获取看样顾问
   *
   * @return consultant - 看样顾问
   */
  public String getConsultant() {
    return consultant;
  }

  /**
   * 设置看样顾问
   *
   * @param consultant 看样顾问
   */
  public void setConsultant(String consultant) {
    this.consultant = consultant;
  }

  /**
   * 获取看样现场问题汇总
   *
   * @return problems - 看样现场问题汇总
   */
  public String getProblems() {
    return problems;
  }

  /**
   * 设置看样现场问题汇总
   *
   * @param problems 看样现场问题汇总
   */
  public void setProblems(String problems) {
    this.problems = problems;
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

  public void setCorporeHouseId(Long corporeHouseId) {
    this.corporeHouseId = corporeHouseId;
  }

  public Long getCorporeHouseId() {
    return corporeHouseId;
  }
}
