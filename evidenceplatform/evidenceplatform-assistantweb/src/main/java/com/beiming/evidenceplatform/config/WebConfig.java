package com.beiming.evidenceplatform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author tp Date : 2018/6/3/003 22:48
 */
@Component
public class WebConfig extends WebMvcConfigurerAdapter {

  @Value("${file.storage.prefix}")
  private String prefix;

  @Value("${file.storage.root.dir}")
  private String location;
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler(prefix + "**")
        .addResourceLocations("file:///" + location);
  }

}
