package com.beiming.evidenceplatform.common.mail;

import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.beiming.evidenceplatform.common.mail.model.EmailProperties;

/**
 * @author tp Date : 2018/6/1/001 16:10
 */
@Configuration
public class JavaMailConfig {

  
  @Resource
  private EmailProperties emailProperties;

  @Bean(name = "javaMailSender")
  public JavaMailSender mailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(emailProperties.getHost());
    mailSender.setUsername(emailProperties.getUsername());
    mailSender.setPassword(emailProperties.getPassword());
    mailSender.setDefaultEncoding("UTF-8");
    Properties prop = new Properties();
    // 设定properties
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.timeout", "25000");
    // 设置调试模式可以在控制台查看发送过程
    prop.put("mail.debug", "true");
    prop.put("mail.smtp.ssl.enable", "true");
    prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    mailSender.setJavaMailProperties(prop);
    return mailSender;
  }
}
