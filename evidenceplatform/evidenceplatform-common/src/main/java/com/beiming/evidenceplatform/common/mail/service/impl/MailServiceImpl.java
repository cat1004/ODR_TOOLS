package com.beiming.evidenceplatform.common.mail.service.impl;

import com.beiming.evidenceplatform.common.mail.model.Email;
import com.beiming.evidenceplatform.common.mail.model.EmailProperties;
import com.beiming.evidenceplatform.common.mail.service.IMailService;
import com.beiming.evidenceplatform.common.mail.util.MailUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 * @description:
 * @author: tangping
 **/

@Service
public class MailServiceImpl implements IMailService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

  @Resource
  private JavaMailSender mailSender;

  @Resource
  private Configuration configuration;

  @Resource
  private EmailProperties emailProperties;

  private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

  static {
    System.setProperty("mail.mime.splitlongparameters", "false");
  }

  @Override
  public void send(Email mail) {
    LOGGER.info("发送邮件：{}", mail.getContent());
    MailUtil mailUtil = new MailUtil();
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(emailProperties.getFrom());
    message.setTo(mail.getEmail());
    message.setSubject(mail.getSubject());
    message.setText(mail.getContent());
    mailUtil.start(mailSender, message);
  }

  @Override
  public void sendHtml(Email mail) {
    MailUtil mailUtil = new MailUtil();
    MimeMessage message = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(emailProperties.getFrom());
      helper.setTo(mail.getEmail());
      helper.setSubject(mail.getSubject());
      helper.setText(
          "<html><body><img src=\"cid:springcloud\" ></body></html>",
          true);
      mailUtil.startHtml(mailSender, message);
    } catch (Exception e) {
      logger.error("send email by html is error:{}", e.getMessage());
    }
  }

  @Override
  public void sendFreemarker(Email mail) {
    MimeMessage message = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(emailProperties.getFrom());
      helper.setTo(mail.getEmail());
      helper.setSubject(mail.getSubject());
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("content", mail.getContent());
      Template template = configuration.getTemplate(mail.getTemplate() + ".flt");
      String text = FreeMarkerTemplateUtils.processTemplateIntoString(
          template, model);
      helper.setText(text, true);
      mailSender.send(message);
    } catch (Exception e) {
      logger.error("send email by template is error:{}", e.getMessage());
    }

  }
}