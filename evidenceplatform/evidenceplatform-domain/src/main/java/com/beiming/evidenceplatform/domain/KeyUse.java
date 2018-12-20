package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

@Table(name = "key_use")
public class KeyUse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 使用人名字
   */
  @Column(name = "user_name")
  private String userName;

  /**
   * 数量
   */
  @Column(name = "key_num")
  private Integer keyNum;

  /**
   * 用途
   */
  private String usering;

  /**
   * 预计归还时间
   */
  @Column(name = "order_back_time")
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private Date orderBackTime;
  

  /**
   * 钥匙名称
   */
  @Column(name = "key_name")
  private String keyName;

  /**
   * 使用时间
   */
  @Column(name = "user_time")
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private Date userTime;

  @Column(name = "corpore_house_id")
  private Long corporeHouseId;

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
   * 使用状态
   */
  @Column(name = "status")
  private Integer status;

  /**
   * 版本号
   */
  private Integer version;

  /**
   * 备注
   */
  private String remark;


  @Column(name = "key_id")
  private Long keyId;

  /**
   * 实际归还时间
   */
  @Column(name = "real_back_time")
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private Date realBackTime;

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
   * 获取使用人名字
   *
   * @return user_name - 使用人名字
   */
  public String getUserName() {
    return userName;
  }

  /**
   * 设置使用人名字
   *
   * @param userName 使用人名字
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * 获取数量
   *
   * @return key_num - 数量
   */
  public Integer getKeyNum() {
    return keyNum;
  }

  /**
   * 设置数量
   *
   * @param keyNum 数量
   */
  public void setKeyNum(Integer keyNum) {
    this.keyNum = keyNum;
  }

  /**
   * 获取用途
   *
   * @return usering - 用途
   */
  public String getUsering() {
    return usering;
  }

  /**
   * 设置用途
   *
   * @param usering 用途
   */
  public void setUsering(String usering) {
    this.usering = usering;
  }

  /**
   * 获取预计归还时间
   *
   * @return order_back_time - 预计归还时间
   */
  public Date getOrderBackTime() {
    return orderBackTime;
  }

  /**
   * 设置预计归还时间
   *
   * @param orderBackTime 预计归还时间
   */
  public void setOrderBackTime(Date orderBackTime) {
    this.orderBackTime = orderBackTime;
  }

  /**
   * 获取钥匙名称
   *
   * @return key_name - 钥匙名称
   */
  public String getKeyName() {
    return keyName;
  }

  /**
   * 设置钥匙名称
   *
   * @param keyName 钥匙名称
   */
  public void setKeyName(String keyName) {
    this.keyName = keyName;
  }

  /**
   * 获取使用时间
   *
   * @return user_time - 使用时间
   */
  public Date getUserTime() {
    return userTime;
  }

  /**
   * 设置使用时间
   *
   * @param userTime 使用时间
   */
  public void setUserTime(Date userTime) {
    this.userTime = userTime;
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

  /**
<<<<<<< HEAD
   * 获取使用状态，1-使用中 2-归还
   *
   * @return status - 使用状态，1-使用中 2-归还
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * 设置使用状态，1-使用中 2-归还
   *
   * @param status 使用状态，1-使用中 2-归还
   */
  public void setStatus(Integer status) {
    this.status = status;
  }

  /**
   * @return key_id
=======
   * @return the keyId
>>>>>>> 44099979c452a667ab615f9320d038f6f2d013d3
   */
  public Long getKeyId() {
    return keyId;
  }

  /**
<<<<<<< HEAD
   * @param keyId
=======
   * @param keyId the keyId to set
>>>>>>> 44099979c452a667ab615f9320d038f6f2d013d3
   */
  public void setKeyId(Long keyId) {
    this.keyId = keyId;
  }

  /**
<<<<<<< HEAD
   * 获取实际归还时间
   *
   * @return real_back_time - 实际归还时间
=======
   * @return the realBackTime
>>>>>>> 44099979c452a667ab615f9320d038f6f2d013d3
   */
  public Date getRealBackTime() {
    return realBackTime;
  }

  /**
<<<<<<< HEAD
   * 设置实际归还时间
   *
   * @param realBackTime 实际归还时间
=======
   * @param realBackTime the realBackTime to set
>>>>>>> 44099979c452a667ab615f9320d038f6f2d013d3
   */
  public void setRealBackTime(Date realBackTime) {
    this.realBackTime = realBackTime;
  }
}
