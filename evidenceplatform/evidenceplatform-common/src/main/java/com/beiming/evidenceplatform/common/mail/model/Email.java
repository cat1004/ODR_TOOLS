package com.beiming.evidenceplatform.common.mail.model;

import java.io.Serializable;
import java.util.HashMap;
import lombok.Data;

/**
 * @description: Email封装类
 * @author: wangfuming
 * @create: 2018年03月09日 17:09
 **/
@Data
public class Email implements Serializable {

  private static final long serialVersionUID = 1L;
  //必填参数
  private String[] email;
  private String subject;
  private String content;
  //选填
  private String template;
  private HashMap<String, String> kvMap;
  public String[] getEmail() {
    return email;
  }
  public void setEmail(String[] email) {
    this.email = email;
  }
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getTemplate() {
    return template;
  }
  public void setTemplate(String template) {
    this.template = template;
  }
  public HashMap<String, String> getKvMap() {
    return kvMap;
  }
  public void setKvMap(HashMap<String, String> kvMap) {
    this.kvMap = kvMap;
  }
  public static long getSerialversionuid() {
    return serialVersionUID;
  }
  
}
