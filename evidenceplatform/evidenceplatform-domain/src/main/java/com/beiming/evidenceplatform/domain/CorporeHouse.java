package com.beiming.evidenceplatform.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "corpore_house")
public class CorporeHouse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 勘验
   */
  @Column(name = "survey_id")
  private Long surveyId;

  private String name; //标的物名称

  /**
   * 楼层
   */
  private String floor;

  @Column(name = "corpore_id")
  private Long corporeId;

  @Column(name = "auction_detail_id")
  private Long auctionDetailId;

  /**
   * 标的物详情描述
   */
  @Column(name = "detail_content")
  private String detailContent;

  /**
   * 用途
   */
  private String usering;

  /**
   * 年代
   */
  private String years;

  /**
   * 朝向
   */
  private String orientations;

  /**
   * 面积
   */
  private String acreage;

  /**
   * 户型
   */
  @Column(name = "house_type")
  private String houseType;

  /**
   * 土地性质
   */
  @Column(name = "land_nature")
  private String landNature;

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
   * 一个房产对应多个预约
   */
  private List<OrderWatch> orderWatchs;
  
  
  /**
   * 一个房产对应一个标的物
   */
  private Corpore corpore;
  

  public Corpore getCorpore() {
    return corpore;
  }

  public void setCorpore(Corpore corpore) {
    this.corpore = corpore;
  }

  public List<OrderWatch> getOrderWatchs() {
    return orderWatchs;
  }

  public void setOrderWatchs(List<OrderWatch> orderWatchs) {
    this.orderWatchs = orderWatchs;
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
   * 获取勘验
   *
   * @return survey_id - 勘验
   */
  public Long getSurveyId() {
    return surveyId;
  }

  /**
   * 设置勘验
   *
   * @param surveyId 勘验
   */
  public void setSurveyId(Long surveyId) {
    this.surveyId = surveyId;
  }


  /**
   * 获取楼层
   *
   * @return floor - 楼层
   */
  public String getFloor() {
    return floor;
  }

  /**
   * 设置楼层
   *
   * @param floor 楼层
   */
  public void setFloor(String floor) {
    this.floor = floor;
  }



  
  /**
   * @return corpore_id
   */
  public Long getCorporeId() {
    return corporeId;
  }

  /**
   * @param corporeId
   */
  public void setCorporeId(Long corporeId) {
    this.corporeId = corporeId;
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
   * 获取标的物详情描述
   *
   * @return detail_content - 标的物详情描述
   */
  public String getDetailContent() {
    return detailContent;
  }

  /**
   * 设置标的物详情描述
   *
   * @param detailContent 标的物详情描述
   */
  public void setDetailContent(String detailContent) {
    this.detailContent = detailContent;
  }

  /**
   * 获取用途
   *
   * @return usering - 用途
   */
  public String getUsering() {
    return usering;
  }

  /**
   * 设置用途
   *
   * @param usering 用途
   */
  public void setUsering(String usering) {
    this.usering = usering;
  }

  /**
   * 获取年代
   *
   * @return years - 年代
   */
  public String getYears() {
    return years;
  }

  /**
   * 设置年代
   *
   * @param years 年代
   */
  public void setYears(String years) {
    this.years = years;
  }

  /**
   * 获取朝向
   *
   * @return orientations - 朝向
   */
  public String getOrientations() {
    return orientations;
  }

  /**
   * 设置朝向
   *
   * @param orientations 朝向
   */
  public void setOrientations(String orientations) {
    this.orientations = orientations;
  }

  /**
   * 获取面积
   *
   * @return acreage - 面积
   */
  public String getAcreage() {
    return acreage;
  }

  /**
   * 设置面积
   *
   * @param acreage 面积
   */
  public void setAcreage(String acreage) {
    this.acreage = acreage;
  }

  /**
   * 获取户型
   *
   * @return house_type - 户型
   */
  public String getHouseType() {
    return houseType;
  }

  /**
   * 设置户型
   *
   * @param houseType 户型
   */
  public void setHouseType(String houseType) {
    this.houseType = houseType;
  }

  /**
   * 获取土地性质
   *
   * @return land_nature - 土地性质
   */
  public String getLandNature() {
    return landNature;
  }

  /**
   * 设置土地性质
   *
   * @param landNature 土地性质
   */
  public void setLandNature(String landNature) {
    this.landNature = landNature;
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

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
