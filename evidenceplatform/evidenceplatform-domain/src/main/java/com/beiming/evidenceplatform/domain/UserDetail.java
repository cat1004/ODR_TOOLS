package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_detail")
public class UserDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  /**
   * 身份标记
   */
  private String role;

  /**
   * 真实名字
   */
  @Column(name = "actual_name")
  private String actualName;

  @Column(name = "user_id")
  private Long userId;

  private String address;

  /**
   * 用户电话
   */
  private String phone;

  /**
   * 公司
   */
  private String company;

  /**
   * 公司地址
   */
  @Column(name = "company_address")
  private String companyAddress;

  private String deptname;

  /**
   * 职位
   */
  private String position;

  /**
   * 所在地区
   */
  @Column(name = "area_id")
  private String areaId;

  /**
   * 性别
   */
  private String sex;

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
   * 身份证号
   */
  @Column(name = "id_card")
  private String idCard;

  /**
   * 版本号
   */
  private Integer version;

  /**
   * 备注
   */
  private String remark;

  /**
   * 用户空间总容量
   */
  private String capacity;

  /**
   * 分配空间容量
   */
  private String usageAmount;

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
   * 获取身份标记
   *
   * @return role - 身份标记
   */
  public String getRole() {
    return role;
  }

  /**
   * 设置身份标记
   *
   * @param role 身份标记
   */
  public void setRole(String role) {
    this.role = role;
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
   * @return user_id
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * @param userId
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * @return address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @param address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * 获取用户电话
   *
   * @return phone - 用户电话
   */
  public String getPhone() {
    return phone;
  }

  /**
   * 设置用户电话
   *
   * @param phone 用户电话
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * 获取公司
   *
   * @return company - 公司
   */
  public String getCompany() {
    return company;
  }

  /**
   * 设置公司
   *
   * @param company 公司
   */
  public void setCompany(String company) {
    this.company = company;
  }

  /**
   * 获取公司地址
   *
   * @return company_address - 公司地址
   */
  public String getCompanyAddress() {
    return companyAddress;
  }

  /**
   * 设置公司地址
   *
   * @param companyAddress 公司地址
   */
  public void setCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
  }

  /**
   * @return deptname
   */
  public String getDeptname() {
    return deptname;
  }

  /**
   * @param deptname
   */
  public void setDeptname(String deptname) {
    this.deptname = deptname;
  }

  /**
   * 获取职位
   *
   * @return position - 职位
   */
  public String getPosition() {
    return position;
  }

  /**
   * 设置职位
   *
   * @param position 职位
   */
  public void setPosition(String position) {
    this.position = position;
  }

  /**
   * 获取所在地区
   *
   * @return area_id - 所在地区
   */
  public String getAreaId() {
    return areaId;
  }

  /**
   * 设置所在地区
   *
   * @param areaId 所在地区
   */
  public void setAreaId(String areaId) {
    this.areaId = areaId;
  }

  /**
   * 获取性别
   *
   * @return sex - 性别
   */
  public String getSex() {
    return sex;
  }

  /**
   * 设置性别
   *
   * @param sex 性别
   */
  public void setSex(String sex) {
    this.sex = sex;
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

  public String getCapacity() {
    return capacity;
  }

  public void setCapacity(String capacity) {
    this.capacity = capacity;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public String getUsageAmount() {
    return usageAmount;
  }

  public void setUsageAmount(String usageAmount) {
    this.usageAmount = usageAmount;
  }


}
