package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

public class Orgnazation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "parent_id")
  private Long parentId;

  /**
   * 机构名字
   */
  @Column(name = "organization_name")
  private String organizationName;

  /**
   * 机构类型
   */
  private String type;

  /**
   * 机构地址
   */
  @Column(name = "organization_address")
  private String organizationAddress;

  /**
   * 行政级别号
   */
  @Column(name = "grade_level")
  private String gradeLevel;

  /**
   * 地区级别
   */
  @Column(name = "area_level")
  private String areaLevel;

  /**
   * 地区id
   */
  @Column(name = "area_id")
  private String areaId;

  /**
   * 上线--下线
   */
  private String status;

  /**
   * 机构地区
   */
  @Column(name = "organization_area")
  private String organizationArea;

  /**
   * 上级机构名称
   */
  @Column(name = "super_name")
  private String superName;

  /**
   * 行政级别
   */
  private String grade;

  /**
   * 组织机构代码
   */
  @Column(name = "organization_code")
  private String organizationCode;

  /**
   * 机构详细地址
   */
  @Column(name = "detail_address")
  private String detailAddress;

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
   * @return parent_id
   */
  public Long getParentId() {
    return parentId;
  }

  /**
   * @param parentId
   */
  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  /**
   * 获取机构名字
   *
   * @return organization_name - 机构名字
   */
  public String getOrganizationName() {
    return organizationName;
  }

  /**
   * 设置机构名字
   *
   * @param organizationName 机构名字
   */
  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  /**
   * 获取机构类型
   *
   * @return type - 机构类型
   */
  public String getType() {
    return type;
  }

  /**
   * 设置机构类型
   *
   * @param type 机构类型
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * 获取机构地址
   *
   * @return organization_address - 机构地址
   */
  public String getOrganizationAddress() {
    return organizationAddress;
  }

  /**
   * 设置机构地址
   *
   * @param organizationAddress 机构地址
   */
  public void setOrganizationAddress(String organizationAddress) {
    this.organizationAddress = organizationAddress;
  }

  /**
   * 获取行政级别号
   *
   * @return grade_level - 行政级别号
   */
  public String getGradeLevel() {
    return gradeLevel;
  }

  /**
   * 设置行政级别号
   *
   * @param gradeLevel 行政级别号
   */
  public void setGradeLevel(String gradeLevel) {
    this.gradeLevel = gradeLevel;
  }

  /**
   * 获取地区级别
   *
   * @return area_level - 地区级别
   */
  public String getAreaLevel() {
    return areaLevel;
  }

  /**
   * 设置地区级别
   *
   * @param areaLevel 地区级别
   */
  public void setAreaLevel(String areaLevel) {
    this.areaLevel = areaLevel;
  }

  /**
   * 获取地区id
   *
   * @return area_id - 地区id
   */
  public String getAreaId() {
    return areaId;
  }

  /**
   * 设置地区id
   *
   * @param areaId 地区id
   */
  public void setAreaId(String areaId) {
    this.areaId = areaId;
  }

  /**
   * 获取上线--下线
   *
   * @return status - 上线--下线
   */
  public String getStatus() {
    return status;
  }

  /**
   * 设置上线--下线
   *
   * @param status 上线--下线
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * 获取机构地区
   *
   * @return organization_area - 机构地区
   */
  public String getOrganizationArea() {
    return organizationArea;
  }

  /**
   * 设置机构地区
   *
   * @param organizationArea 机构地区
   */
  public void setOrganizationArea(String organizationArea) {
    this.organizationArea = organizationArea;
  }

  /**
   * 获取上级机构名称
   *
   * @return super_name - 上级机构名称
   */
  public String getSuperName() {
    return superName;
  }

  /**
   * 设置上级机构名称
   *
   * @param superName 上级机构名称
   */
  public void setSuperName(String superName) {
    this.superName = superName;
  }

  /**
   * 获取行政级别
   *
   * @return grade - 行政级别
   */
  public String getGrade() {
    return grade;
  }

  /**
   * 设置行政级别
   *
   * @param grade 行政级别
   */
  public void setGrade(String grade) {
    this.grade = grade;
  }

  /**
   * 获取组织机构代码
   *
   * @return organization_code - 组织机构代码
   */
  public String getOrganizationCode() {
    return organizationCode;
  }

  /**
   * 设置组织机构代码
   *
   * @param organizationCode 组织机构代码
   */
  public void setOrganizationCode(String organizationCode) {
    this.organizationCode = organizationCode;
  }

  /**
   * 获取机构详细地址
   *
   * @return detail_address - 机构详细地址
   */
  public String getDetailAddress() {
    return detailAddress;
  }

  /**
   * 设置机构详细地址
   *
   * @param detailAddress 机构详细地址
   */
  public void setDetailAddress(String detailAddress) {
    this.detailAddress = detailAddress;
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
