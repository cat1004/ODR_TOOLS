package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "auction_notice")
public class AuctionNotice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 拍卖公告、须知、重要提示
   */
  private String content;

  /**
   * 类型、公告、须知、重要提示
   */
  private String type;

  @Column(name = "auction_detail_id")
  private Long auctionDetailId;

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
   * 获取拍卖公告、须知、重要提示
   *
   * @return content - 拍卖公告、须知、重要提示
   */
  public String getContent() {
    return content;
  }

  /**
   * 设置拍卖公告、须知、重要提示
   *
   * @param content 拍卖公告、须知、重要提示
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * 获取类型、公告、须知、重要提示
   *
   * @return type - 类型、公告、须知、重要提示
   */
  public String getType() {
    return type;
  }

  /**
   * 设置类型、公告、须知、重要提示
   *
   * @param type 类型、公告、须知、重要提示
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return auction_detail_id
   */
  public Long getAuctionDetailId() {
    return auctionDetailId;
  }

  /**
   * @param auctionDetailId
   */
  public void setAuctionDetailId(Long auctionDetailId) {
    this.auctionDetailId = auctionDetailId;
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