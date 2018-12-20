package com.beiming.evidenceplatform.domain;

import javax.persistence.*;

public class Test {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String sex;

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
   * @return sex
   */
  public String getSex() {
    return sex;
  }

  /**
   * @param sex
   */
  public void setSex(String sex) {
    this.sex = sex;
  }
}
