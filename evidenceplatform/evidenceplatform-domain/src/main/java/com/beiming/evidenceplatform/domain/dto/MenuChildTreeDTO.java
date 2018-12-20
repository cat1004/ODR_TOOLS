package com.beiming.evidenceplatform.domain.dto;

import java.util.List;


public class MenuChildTreeDTO {
  private String id;
  private String text;
  private List<MenuChildTreeDTO> children;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public List<MenuChildTreeDTO> getChildren() {
    return children;
  }

  public void setChildren(List<MenuChildTreeDTO> children) {
    this.children = children;
  }
}
