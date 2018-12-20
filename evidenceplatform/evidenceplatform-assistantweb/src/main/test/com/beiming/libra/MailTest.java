package com.beiming.libra;

import com.beiming.libra.common.mail.model.Email;
import com.beiming.libra.common.mail.service.IMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author tp Date : 2018/6/1/001 16:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailTest {

  @Autowired
  IMailService mailService;

  @Test
  public void test() throws Exception {
    Email mail = new Email();
    mail.setContent("新闻用户");
    mail.setSubject("新闻用户");
    mail.setEmail(new String[]{"XXXx@qq.com"});
    mailService.send(mail);
  }
}
