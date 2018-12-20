package com.beiming.evidenceplatform.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "order_watch")
public class OrderWatch {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 预约时间
   *///
  
  @Column(name = "order_time")
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private Date orderTime;

  @Column(name = "corpore_house_id")
  private Long corporeHouseId;

  /**
   * 创建时间
   */
  @JsonIgnore
  @Column(name = "create_time")
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  /**
   * 更新时间
   */
  @JsonIgnore
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
  @JsonIgnore
  @Column(name = "update_user")
  private String updateUser;

  /**
   * 版本号
   */
  @JsonIgnore
  private Integer version;

  /**
   * 备注
   */
  private String remark;


  /**
   * 一对多详情，@JsonIgnore此属性屏蔽掉swgger2使用对象实例的属性
   */
  @JsonIgnore
  private List<OrderWatchPersonnel> orderWatchPersonnellist;


  /**
   * @return id
   */



  /**
   * 获取预约时间
   *
   * @return order_time - 预约时间
   */
  public Date getOrderTime() {
    return orderTime;
  }

  public List<OrderWatchPersonnel> getOrderWatchPersonnellist() {
    return orderWatchPersonnellist;
  }

  public void setOrderWatchPersonnellist(List<OrderWatchPersonnel> orderWatchPersonnellist) {
    this.orderWatchPersonnellist = orderWatchPersonnellist;
  }

  /**
   * 设置预约时间
   *
   * @param orderTime 预约时间
   */
  public void setOrderTime(Date orderTime) {
    this.orderTime = orderTime;
  }



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCorporeHouseId() {
    return corporeHouseId;
  }

  public void setCorporeHouseId(Long corporeHouseId) {
    this.corporeHouseId = corporeHouseId;
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
