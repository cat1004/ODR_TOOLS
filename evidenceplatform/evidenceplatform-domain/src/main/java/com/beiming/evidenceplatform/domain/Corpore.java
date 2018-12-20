package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Corpore {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 标的名字
   */
  private String name;

  /**
   * 标的编号
   */
  private String no;

  /**
   * 拍卖时间
   */
  @Column(name = "auction_time")
  private Date auctionTime;

  @Column(name = "assistanter_id")
  private Long assistanterId;

  /**
   * 标的物所在地址
   */
  @Column(name = "area_id")
  private String areaId;

  /**
   * 组织id
   */
  @Column(name = "org_id")
  private Long orgId;

  /**
   * 服务人员id
   */
  @Column(name = "service_per_id")
  private Long servicePerId;

  /**
   * 标的类型
   */
  private String type;

  /**
   * 拍卖状态
   */
  private String status;

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

  private String groundingtype;

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
   * 获取标的名字
   *
   * @return name - 标的名字
   */
  public String getName() {
    return name;
  }

  /**
   * 设置标的名字
   *
   * @param name 标的名字
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 获取标的编号
   *
   * @return no - 标的编号
   */
  public String getNo() {
    return no;
  }

  /**
   * 设置标的编号
   *
   * @param no 标的编号
   */
  public void setNo(String no) {
    this.no = no;
  }

  /**
   * 获取拍卖时间
   *
   * @return auction_time - 拍卖时间
   */
  public Date getAuctionTime() {
    return auctionTime;
  }

  /**
   * 设置拍卖时间
   *
   * @param auctionTime 拍卖时间
   */
  public void setAuctionTime(Date auctionTime) {
    this.auctionTime = auctionTime;
  }

  /**
   * @return assistanter_id
   */
  public Long getAssistanterId() {
    return assistanterId;
  }

  /**
   * @param assistanterId
   */
  public void setAssistanterId(Long assistanterId) {
    this.assistanterId = assistanterId;
  }

  /**
   * 获取标的物所在地址
   *
   * @return area_id - 标的物所在地址
   */
  public String getAreaId() {
    return areaId;
  }

  /**
   * 设置标的物所在地址
   *
   * @param areaId 标的物所在地址
   */
  public void setAreaId(String areaId) {
    this.areaId = areaId;
  }

  /**
   * 获取组织id
   *
   * @return org_id - 组织id
   */
  public Long getOrgId() {
    return orgId;
  }

  /**
   * 设置组织id
   *
   * @param orgId 组织id
   */
  public void setOrgId(Long orgId) {
    this.orgId = orgId;
  }

  /**
   * 获取服务人员id
   *
   * @return service_per_id - 服务人员id
   */
  public Long getServicePerId() {
    return servicePerId;
  }

  /**
   * 设置服务人员id
   *
   * @param servicePerId 服务人员id
   */
  public void setServicePerId(Long servicePerId) {
    this.servicePerId = servicePerId;
  }

  /**
   * 获取标的类型
   *
   * @return type - 标的类型
   */
  public String getType() {
    return type;
  }

  /**
   * 设置标的类型
   *
   * @param type 标的类型
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * 获取拍卖状态
   *
   * @return status - 拍卖状态
   */
  public String getStatus() {
    return status;
  }

  /**
   * 设置拍卖状态
   *
   * @param status 拍卖状态
   */
  public void setStatus(String status) {
    this.status = status;
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
   * @return groundingtype
   */
  public String getGroundingtype() {
    return groundingtype;
  }

  /**
   * @param groundingtype
   */
  public void setGroundingtype(String groundingtype) {
    this.groundingtype = groundingtype;
  }
}
