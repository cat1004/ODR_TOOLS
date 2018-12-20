package com.beiming.evidenceplatform.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "photo_files")
public class PhotoFiles {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "survey_id")
  private Long surveyId;

  /**
   * 图片名字
   */
  private String name;

  /**
   * 图片地址(fastDFS)
   */
  private String url;

  @Column(name = "recordings_id")
  private Long recordingsId;

  @Column(name = "auction_notice_id")
  private Long auctionNoticeId;

  @Column(name = "corpore_house_id")
  private Long corporeHouseId;

  @Column(name = "del_flag")
  private String delFlag;

  private String content;

  private String type;

  @Column(name = "delivery_pd_id")
  private Long deliveryPdId;

  @Column(name = "real_condition_id")
  private Long realConditionId;

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
   * @return survey_id
   */
  public Long getSurveyId() {
    return surveyId;
  }

  /**
   * @param surveyId
   */
  public void setSurveyId(Long surveyId) {
    this.surveyId = surveyId;
  }

  /**
   * 获取图片名字
   *
   * @return name - 图片名字
   */
  public String getName() {
    return name;
  }

  /**
   * 设置图片名字
   *
   * @param name 图片名字
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 获取图片地址(fastDFS)
   *
   * @return url - 图片地址(fastDFS)
   */
  public String getUrl() {
    return url;
  }

  /**
   * 设置图片地址(fastDFS)
   *
   * @param url 图片地址(fastDFS)
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /**
   * @return recordings_id
   */
  public Long getRecordingsId() {
    return recordingsId;
  }

  /**
   * @param recordingsId
   */
  public void setRecordingsId(Long recordingsId) {
    this.recordingsId = recordingsId;
  }

  /**
   * @return auction_notice_id
   */
  public Long getAuctionNoticeId() {
    return auctionNoticeId;
  }

  /**
   * @param auctionNoticeId
   */
  public void setAuctionNoticeId(Long auctionNoticeId) {
    this.auctionNoticeId = auctionNoticeId;
  }

  /**
   * @return corpore_house_id
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
   * @return del_flag
   */
  public String getDelFlag() {
    return delFlag;
  }

  /**
   * @param delFlag
   */
  public void setDelFlag(String delFlag) {
    this.delFlag = delFlag;
  }

  /**
   * @return content
   */
  public String getContent() {
    return content;
  }

  /**
   * @param content
   */
  public void setContent(String content) {
    this.content = content;
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
   * @return delivery_pd_id
   */
  public Long getDeliveryPdId() {
    return deliveryPdId;
  }

  /**
   * @param deliveryPdId
   */
  public void setDeliveryPdId(Long deliveryPdId) {
    this.deliveryPdId = deliveryPdId;
  }

  /**
   * @return real_condition_id
   */
  public Long getRealConditionId() {
    return realConditionId;
  }

  /**
   * @param realConditionId
   */
  public void setRealConditionId(Long realConditionId) {
    this.realConditionId = realConditionId;
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