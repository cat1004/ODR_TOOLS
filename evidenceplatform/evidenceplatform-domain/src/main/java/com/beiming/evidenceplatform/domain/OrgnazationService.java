package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "orgnazation_service")
public class OrgnazationService {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "spm_id")
  private Long spmId;

  @Column(name = "org_id")
  private Long orgId;

  @Column(name = "spm_name")
  private String spmName;

  @Column(name = "area_name")
  private String areaName;

  @Column(name = "area_id")
  private String areaId;

  @Column(name = "service_code")
  private String serviceCode;

  @Column(name = "service_type")
  private String serviceType;

  @Column(name = "org_name")
  private String orgName;

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
   * @return spm_id
   */
  public Long getSpmId() {
    return spmId;
  }

  /**
   * @param spmId
   */
  public void setSpmId(Long spmId) {
    this.spmId = spmId;
  }

  /**
   * @return org_id
   */
  public Long getOrgId() {
    return orgId;
  }

  /**
   * @param orgId
   */
  public void setOrgId(Long orgId) {
    this.orgId = orgId;
  }

  /**
   * @return spm_name
   */
  public String getSpmName() {
    return spmName;
  }

  /**
   * @param spmName
   */
  public void setSpmName(String spmName) {
    this.spmName = spmName;
  }

  /**
   * @return area_name
   */
  public String getAreaName() {
    return areaName;
  }

  /**
   * @param areaName
   */
  public void setAreaName(String areaName) {
    this.areaName = areaName;
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
   * @return service_code
   */
  public String getServiceCode() {
    return serviceCode;
  }

  /**
   * @param serviceCode
   */
  public void setServiceCode(String serviceCode) {
    this.serviceCode = serviceCode;
  }

  /**
   * @return service_type
   */
  public String getServiceType() {
    return serviceType;
  }

  /**
   * @param serviceType
   */
  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }

  /**
   * @return org_name
   */
  public String getOrgName() {
    return orgName;
  }

  /**
   * @param orgName
   */
  public void setOrgName(String orgName) {
    this.orgName = orgName;
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
