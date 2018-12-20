package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

public class Personnel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 角色（法官、法官管理人、辅助人员、用户）
   */
  private String role;

  /**
   * 个人、企业
   */
  private String type;

  /**
   * userdetailid或者servicePersonnelid
   */
  @Column(name = "user_detail_id")
  private Long userDetailId;

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
   * 状态
   */
  private String status;

  /**
   * 获取状态
   */
  public String getStatus() {
    return status;
  }

  /**
   * 设置状态
   */
  public void setStatus(String status) {
    this.status = status;
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
   * 获取角色（法官、法官管理人、辅助人员、用户）
   *
   * @return role - 角色（法官、法官管理人、辅助人员、用户）
   */
  public String getRole() {
    return role;
  }

  /**
   * 设置角色（法官、法官管理人、辅助人员、用户）
   *
   * @param role 角色（法官、法官管理人、辅助人员、用户）
   */
  public void setRole(String role) {
    this.role = role;
  }

  /**
   * 获取个人、企业
   *
   * @return type - 个人、企业
   */
  public String getType() {
    return type;
  }

  /**
   * 设置个人、企业
   *
   * @param type 个人、企业
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * 获取userdetailid或者servicePersonnelid
   *
   * @return user_detail_id - userdetailid或者servicePersonnelid
   */
  public Long getUserDetailId() {
    return userDetailId;
  }

  /**
   * 设置userdetailid或者servicePersonnelid
   *
   * @param userDetailId userdetailid或者servicePersonnelid
   */
  public void setUserDetailId(Long userDetailId) {
    this.userDetailId = userDetailId;
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
