package com.beiming.evidenceplatform.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Column(name = "last_seen")
  private Date lastSeen;

  private String phone;

  @Column(name = "id_card")
  private String idCard;

  @Column(name = "create_time")
  private Date createTime;

  @Column(name = "statusIsBanned")
  private boolean statusIsBanned; // 用户是否被禁用

  private String email;

  private String password;

  private Integer isAuthenticate;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getLastSeen() {
    return lastSeen;
  }

  public void setLastSeen(Date lastSeen) {
    this.lastSeen = lastSeen;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getIsAuthenticate() {
    return isAuthenticate;
  }

  public void setIsAuthenticate(Integer isAuthenticate) {
    this.isAuthenticate = isAuthenticate;
  }

  public boolean isStatusIsBanned() {
    return statusIsBanned;
  }

  public void setStatusIsBanned(boolean statusIsBanned) {
    this.statusIsBanned = statusIsBanned;
  }

}
