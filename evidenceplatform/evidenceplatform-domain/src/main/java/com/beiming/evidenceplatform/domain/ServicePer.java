package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "service_per")
public class ServicePer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 账号、手机号码
   */
  private String phone;

  /**
   * 真实名字
   */
  @Column(name = "actual_name")
  private String actualName;

  /**
   * 密码
   */
  private String password;

  /**
   * 身份证号码
   */
  @Column(name = "id_card")
  private String idCard;

  /**
   * 职业
   */
  private String role;

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
   * 获取账号、手机号码
   *
   * @return phone - 账号、手机号码
   */
  public String getPhone() {
    return phone;
  }

  /**
   * 设置账号、手机号码
   *
   * @param phone 账号、手机号码
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * 获取真实名字
   *
   * @return actual_name - 真实名字
   */
  public String getActualName() {
    return actualName;
  }

  /**
   * 设置真实名字
   *
   * @param actualName 真实名字
   */
  public void setActualName(String actualName) {
    this.actualName = actualName;
  }

  /**
   * 获取密码
   *
   * @return password - 密码
   */
  public String getPassword() {
    return password;
  }

  /**
   * 设置密码
   *
   * @param password 密码
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * 获取身份证号码
   *
   * @return id_card - 身份证号码
   */
  public String getIdCard() {
    return idCard;
  }

  /**
   * 设置身份证号码
   *
   * @param idCard 身份证号码
   */
  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  /**
   * 获取职业
   *
   * @return role - 职业
   */
  public String getRole() {
    return role;
  }

  /**
   * 设置职业
   *
   * @param role 职业
   */
  public void setRole(String role) {
    this.role = role;
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
