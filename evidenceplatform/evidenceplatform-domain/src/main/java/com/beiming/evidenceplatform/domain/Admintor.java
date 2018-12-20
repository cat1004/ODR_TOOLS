package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

public class Admintor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(name = "actual_name")
  private String actualName;

  private String password;

  private String phone;

  private String status;

  @Column(name = "login_time")
  private Date loginTime;

  private String type;

  private String headImageUrl; //用户头像图片链接
  @Column(name = "area_id")
  private String areaId;
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

  public String getHeadImageUrl() {
    return headImageUrl;
  }

  public void setHeadImageUrl(String headImageUrl) {
    this.headImageUrl = headImageUrl;
  }

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
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return actual_name
   */
  public String getActualName() {
    return actualName;
  }

  /**
   * @param actualName
   */
  public void setActualName(String actualName) {
    this.actualName = actualName;
  }

  /**
   * @return password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * @param phone
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * @return status
   */
  public String getStatus() {
    return status;
  }

  /**
   * @param status
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * @return login_time
   */
  public Date getLoginTime() {
    return loginTime;
  }

  /**
   * @param loginTime
   */
  public void setLoginTime(Date loginTime) {
    this.loginTime = loginTime;
  }

  /**
   * @return type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return area_id
   */
  public String getAreaId() {
    return areaId;
  }

  /**
   * @param areaId
   */
  public void setAreaId(String areaId) {
    this.areaId = areaId;
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
