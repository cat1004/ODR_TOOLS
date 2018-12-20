package com.beiming.evidenceplatform.config;

import javax.servlet.MultipartConfigElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.beiming.evidenceplatform.common.utils.CommonsUtils;

/**
 * 和文件上传下载的初始化配置信息类
 *
 * @author ljf
 */
@Configuration
@Component
public class MultipartConfig {

  /**
   * <pre>
   * 单个文件大小限制
   * 单位:KB,MB
   */
  @Value("${spring.servlet.multipart.maxFileSize}")
  private String maxFileSize;
  /**
   * <pre>
   * 请求总文件大小限制
   * 单位:KB,MB
   */
  @Value("${spring.servlet.multipart.maxRequestSize}")
  private String maxRequestSize;
  /**
   * <pre>
   * 临时目录
   * eg:
   *   /home/userdir/temp/
   * 注意:最后一个反斜杠"/"必须存在
   */
  @Value("${file.storage.tmp.dir}")
  private String location;


  /**
   * <pre>
   * 文件上传配置
   *   1 设置临时目录
   *   2 设置单个文件大小限制
   *   3 设置多文件上传中文件总大小的限制
   *   4 如果临时目录不存在则创建
   * @author ljf
   */
  @Bean
  public MultipartConfigElement multipartConfigElement() {
    MultipartConfigFactory factory = new MultipartConfigFactory();
    /* 上传文件时,存放文件的临时目录 */
    factory.setLocation(location);
    CommonsUtils.createDirs(location);
    /* 单个文件最大限制 , 单位只支持KB和MB */
    factory.setMaxFileSize(maxFileSize);
    /* 设置总上传数据总大小 */
    factory.setMaxRequestSize(maxRequestSize);
    return factory.createMultipartConfig();
  }


  public String getMaxFileSize() {
    return maxFileSize;
  }

  public void setMaxFileSize(String maxFileSize) {
    this.maxFileSize = maxFileSize;
  }

  public String getMaxRequestSize() {
    return maxRequestSize;
  }

  public void setMaxRequestSize(String maxRequestSize) {
    this.maxRequestSize = maxRequestSize;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

}
