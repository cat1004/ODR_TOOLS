package com.beiming.evidenceplatform.common.mail.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @description: email.properties 自定义属性映射
 * @author: wangfuming
 * @create: 2018年03月09日 11:13
 **/
@Component
@Data
@ConfigurationProperties(prefix = "mail")
@PropertySource(value = "classpath:email.properties", ignoreResourceNotFound = true, encoding = "utf-8")
public class EmailProperties {

  private String host;
  private String from;
  private String username;
  private String password;
  private String confirmFileName;
  private String confirmFilePath;
  private String confirmTimeOut;
  private String codeFileName;
  private String codeFilePath;
  private long codeTimeOut;
  private String passwordFileName;
  private String passwordFilePat;
  private String subAccountPwdFileName;
  private String subAccountPwdfilepath;
  private String log4jerrorToMail;
  public String getHost() {
    return host;
  }
  public void setHost(String host) {
    this.host = host;
  }
  public String getFrom() {
    return from;
  }
  public void setFrom(String from) {
    this.from = from;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getConfirmFileName() {
    return confirmFileName;
  }
  public void setConfirmFileName(String confirmFileName) {
    this.confirmFileName = confirmFileName;
  }
  public String getConfirmFilePath() {
    return confirmFilePath;
  }
  public void setConfirmFilePath(String confirmFilePath) {
    this.confirmFilePath = confirmFilePath;
  }
  public String getConfirmTimeOut() {
    return confirmTimeOut;
  }
  public void setConfirmTimeOut(String confirmTimeOut) {
    this.confirmTimeOut = confirmTimeOut;
  }
  public String getCodeFileName() {
    return codeFileName;
  }
  public void setCodeFileName(String codeFileName) {
    this.codeFileName = codeFileName;
  }
  public String getCodeFilePath() {
    return codeFilePath;
  }
  public void setCodeFilePath(String codeFilePath) {
    this.codeFilePath = codeFilePath;
  }
  public long getCodeTimeOut() {
    return codeTimeOut;
  }
  public void setCodeTimeOut(long codeTimeOut) {
    this.codeTimeOut = codeTimeOut;
  }
  public String getPasswordFileName() {
    return passwordFileName;
  }
  public void setPasswordFileName(String passwordFileName) {
    this.passwordFileName = passwordFileName;
  }
  public String getPasswordFilePat() {
    return passwordFilePat;
  }
  public void setPasswordFilePat(String passwordFilePat) {
    this.passwordFilePat = passwordFilePat;
  }
  public String getSubAccountPwdFileName() {
    return subAccountPwdFileName;
  }
  public void setSubAccountPwdFileName(String subAccountPwdFileName) {
    this.subAccountPwdFileName = subAccountPwdFileName;
  }
  public String getSubAccountPwdfilepath() {
    return subAccountPwdfilepath;
  }
  public void setSubAccountPwdfilepath(String subAccountPwdfilepath) {
    this.subAccountPwdfilepath = subAccountPwdfilepath;
  }
  public String getLog4jerrorToMail() {
    return log4jerrorToMail;
  }
  public void setLog4jerrorToMail(String log4jerrorToMail) {
    this.log4jerrorToMail = log4jerrorToMail;
  }

}
