package com.beiming.evidenceplatform.domain;

import javax.persistence.*;

public class Dict {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String code;

  private String describ;

  private String extend;

  private String type;

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
   * @return code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return describ
   */
  public String getDescrib() {
    return describ;
  }

  /**
   * @param describ
   */
  public void setDescrib(String describ) {
    this.describ = describ;
  }

  /**
   * @return extend
   */
  public String getExtend() {
    return extend;
  }

  /**
   * @param extend
   */
  public void setExtend(String extend) {
    this.extend = extend;
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
}