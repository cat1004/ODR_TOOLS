package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "order_watch_personnel")
public class OrderWatchPersonnel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 预约人员
   */
  @Column(name = "order_name")
  private String orderName;

  /**
   * 预约电话（用来做关联）
   */
  @Column(name = "order_phone")
  private String orderPhone;

  @Column(name = "order_watch_id")
  private Long orderWatchId;

  /**
   * 是否签到
   */
  @Column(name = "is_come")
  private String isCome;

  /**
   * 是否被删除
   */
  @Column(name = "del_flag")
  private String delFlag;

  /**
   * 创建时间
   */
  @Column(name = "create_time")
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  @JsonIgnore
  private Date createTime;

  /**
   * 更新时间
   */
  @Column(name = "update_time")
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  @JsonIgnore
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
   * 获取预约人员
   *
   * @return order_name - 预约人员
   */
  public String getOrderName() {
    return orderName;
  }

  /**
   * 设置预约人员
   *
   * @param orderName 预约人员
   */
  public void setOrderName(String orderName) {
    this.orderName = orderName;
  }

  /**
   * 获取预约电话（用来做关联）
   *
   * @return order_phone - 预约电话（用来做关联）
   */
  public String getOrderPhone() {
    return orderPhone;
  }

  /**
   * 设置预约电话（用来做关联）
   *
   * @param orderPhone 预约电话（用来做关联）
   */
  public void setOrderPhone(String orderPhone) {
    this.orderPhone = orderPhone;
  }

  /**
   * @return order_watch_id
   */
  public Long getOrderWatchId() {
    return orderWatchId;
  }

  /**
   * @param orderWatchId
   */
  public void setOrderWatchId(Long orderWatchId) {
    this.orderWatchId = orderWatchId;
  }

  /**
   * 获取是否签到
   *
   * @return is_come - 是否签到
   */
  public String getIsCome() {
    return isCome;
  }

  /**
   * 设置是否签到
   *
   * @param isCome 是否签到
   */
  public void setIsCome(String isCome) {
    this.isCome = isCome;
  }

  /**
   * 获取是否被删除
   *
   * @return del_flag - 是否被删除
   */
  public String getDelFlag() {
    return delFlag;
  }

  /**
   * 设置是否被删除
   *
   * @param delFlag 是否被删除
   */
  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
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
