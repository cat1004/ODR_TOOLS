package com.beiming.evidenceplatform.common.mail.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @description: 邮箱发送工具类（异步执行，默认重试两次）
 * @author: wangfuming
 * @create: 2018年03月09日 11:13
 **/
public class MailUtil {

  private  Logger logger = LoggerFactory.getLogger(MailUtil.class);

  private ScheduledExecutorService service = Executors.newScheduledThreadPool(6);

  private final AtomicInteger count = new AtomicInteger(1);

  public void start(final JavaMailSender mailSender, final SimpleMailMessage message) {
    service.execute(new Runnable() {
      @Override
      public void run() {
        try {
          if (count.get() == 2) {
            service.shutdown();
          }
          mailSender.send(message);
          logger.debug("send email success");
        } catch (Exception e) {
          logger.error("send email fail", e);
        }
      }
    });
  }

  public void startHtml(final JavaMailSender mailSender, final MimeMessage message) {
    service.execute(new Runnable() {
      @Override
      public void run() {
        try {
          if (count.get() == 2) {
            service.shutdown();
          }
          mailSender.send(message);
        } catch (Exception e) {
          logger.error("send email fail", e);
        }
      }
    });
  }
}
