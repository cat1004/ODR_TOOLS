package com.beiming.evidenceplatform.domain.dto;

import java.util.List;

public class MenuTreeDTO {
  public MenuTreeDTO(String id, String text, String state, List<MenuChildTreeDTO> children) {
    this.setId(id);
    this.setText(text);
    this.setState(state);
    this.setChildren(children);
  }

  private String id;
  private String text;
  private String state;
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

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public List<MenuChildTreeDTO> getChildren() {
    return children;
  }

  public void setChildren(List<MenuChildTreeDTO> children) {
    this.children = children;
  }

}
