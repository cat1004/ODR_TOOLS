package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

import com.alibaba.fastjson.annotation.JSONField;

public class KeyInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 钥匙名字
   */
  @Column(name = "key_name")
  private String keyName;

  /**
   * 钥匙库存数量
   */
  @Column(name = "stock")
  private String stock;


  /**
   * 钥匙数量
   */
  @Column(name = "key_num")
  private Integer keyNum;

  /**
   * 房产id
   */
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
   * 获取钥匙名字
   *
   * @return key_name - 钥匙名字
   */
  public String getKeyName() {
    return keyName;
  }

  /**
   * 设置钥匙名字
   *
   * @param keyName 钥匙名字
   */
  public void setKeyName(String keyName) {
    this.keyName = keyName;
  }

  /**
   * 获取库存
   *
   * @return stock - 库存
   */
  public String getStock() {
    return stock;
  }

  /**
   * 设置库存
   *
   * @param stock 库存
   */
  public void setStock(String stock) {
    this.stock = stock;
  }

  /**
   * 获取钥匙数量
   *
   * @return key_num - 钥匙数量
   */
  public Integer getKeyNum() {
    return keyNum;
  }

  /**
   * 设置钥匙数量
   *
   * @param keyNum 钥匙数量
   */
  public void setKeyNum(Integer keyNum) {
    this.keyNum = keyNum;
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
}
