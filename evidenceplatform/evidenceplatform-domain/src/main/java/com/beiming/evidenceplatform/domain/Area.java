package com.beiming.evidenceplatform.domain;

import javax.persistence.*;

public class Area {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  /**
   * 省份名称
   */
  private String sname;

  /**
   * 所属地级市名称
   */
  private String lname;

  /**
   * 地区级别
   */
  private Integer level;

  /**
   * 父级地区名称
   */
  @Column(name = "parent_id")
  private String parentId;

  @Column(name = "canton_flag")
  private Integer cantonFlag;

  /**
   * 是否显示,0为不显示、1位显示
   */
  @Column(name = "show_flag")
  private Integer showFlag;

  private String name;

  private String nameCode;

  /**
   * @return id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return sname
   */
  public String getSname() {
    return sname;
  }

  /**
   * @param sname
   */
  public void setSname(String sname) {
    this.sname = sname;
  }

  /**
   * @return lname
   */
  public String getLname() {
    return lname;
  }

  /**
   * @param lname
   */
  public void setLname(String lname) {
    this.lname = lname;
  }

  /**
   * @return level
   */
  public Integer getLevel() {
    return level;
  }

  /**
   * @param level
   */
  public void setLevel(Integer level) {
    this.level = level;
  }

  /**
   * @return parent_id
   */
  public String getParentId() {
    return parentId;
  }

  /**
   * @param parentId
   */
  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  /**
   * @return canton_flag
   */
  public Integer getCantonFlag() {
    return cantonFlag;
  }

  /**
   * @param cantonFlag
   */
  public void setCantonFlag(Integer cantonFlag) {
    this.cantonFlag = cantonFlag;
  }

  /**
   * @return show_flag
   */
  public Integer getShowFlag() {
    return showFlag;
  }

  /**
   * @param showFlag
   */
  public void setShowFlag(Integer showFlag) {
    this.showFlag = showFlag;
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
   * @return name_code
   */
  public String getNameCode() {
    return nameCode;
  }

  /**
   * @param nameCode
   */
  public void setNameCode(String nameCode) {
    this.nameCode = nameCode;
  }

}