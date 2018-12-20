package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;
@Table(name = "user")
public class User {
  /**
   * 主键
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 账户
   */
  private String name;

  /**
   * 密码
   */
  private String password;

  private String email;

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
   * 版本号
   */
  private UserDetail userdetail;
  
  
  public UserDetail getUserdetail() {
    return userdetail;
  }

  public void setUserdetail(UserDetail userdetail) {
    this.userdetail = userdetail;
  }

  /**
   * 获取主键
   *
   * @return id - 主键
   */
  public Long getId() {
    return id;
  }

  /**
   * 设置主键
   *
   * @param id 主键
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * 获取账户
   *
   * @return name - 账户
   */
  public String getName() {
    return name;
  }

  /**
   * 设置账户
   *
   * @param name 账户
   */
  public void setName(String name) {
    this.name = name;
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
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email
   */
  public void setEmail(String email) {
    this.email = email;
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
