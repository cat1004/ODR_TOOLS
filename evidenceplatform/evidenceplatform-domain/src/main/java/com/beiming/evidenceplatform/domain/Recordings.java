package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Recordings {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "corpore_house_id")
  private Long corporeHouseId;

  /**
   * 咨询类型
   */
  private String type;

  /**
   * 咨询时间
   */
  @Column(name = "advice_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date adviceTime;

  /**
   * 咨询姓名
   */
  @Column(name = "advice_name")
  private String adviceName;

  /**
   * 咨询电话
   */
  @Column(name = "advice_phone")
  private String advicePhone;

  /**
   * 咨询问题
   */
  @Column(name = "advice_problem")
  private String adviceProblem;

  /**
   * 创建时间
   */
  @Column(name = "create_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  /**
   * 更新时间
   */
  @Column(name = "update_time")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
   * @return corpore_detail_id
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
   * 获取咨询类型
   *
   * @return type - 咨询类型
   */
  public String getType() {
    return type;
  }

  /**
   * 设置咨询类型
   *
   * @param type 咨询类型
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * 获取咨询时间
   *
   * @return advice_time - 咨询时间
   */
  public Date getAdviceTime() {
    return adviceTime;
  }

  /**
   * 设置咨询时间
   *
   * @param adviceTime 咨询时间
   */
  public void setAdviceTime(Date adviceTime) {
    this.adviceTime = adviceTime;
  }

  /**
   * 获取咨询姓名
   *
   * @return advice_name - 咨询姓名
   */
  public String getAdviceName() {
    return adviceName;
  }

  /**
   * 设置咨询姓名
   *
   * @param adviceName 咨询姓名
   */
  public void setAdviceName(String adviceName) {
    this.adviceName = adviceName;
  }

  /**
   * 获取咨询电话
   *
   * @return advice_phone - 咨询电话
   */
  public String getAdvicePhone() {
    return advicePhone;
  }

  /**
   * 设置咨询电话
   *
   * @param advicePhone 咨询电话
   */
  public void setAdvicePhone(String advicePhone) {
    this.advicePhone = advicePhone;
  }

  /**
   * 获取咨询问题
   *
   * @return advice_problem - 咨询问题
   */
  public String getAdviceProblem() {
    return adviceProblem;
  }

  /**
   * 设置咨询问题
   *
   * @param adviceProblem 咨询问题
   */
  public void setAdviceProblem(String adviceProblem) {
    this.adviceProblem = adviceProblem;
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
