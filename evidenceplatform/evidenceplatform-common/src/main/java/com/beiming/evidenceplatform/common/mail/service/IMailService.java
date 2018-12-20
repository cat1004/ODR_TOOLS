package com.beiming.evidenceplatform.common.mail.service;


import com.beiming.evidenceplatform.common.mail.model.Email;

/**
 * @description: ${description}
 * @author: wangfuming
 * @create: 2018年03月09日 17:22
 **/
public interface IMailService {

  /**
   * @description: 发送纯文本邮件
   * @method: send
   * @return: void
   * @date: 2018年03月09日 17:23:28
   * @author: wangfuming
   */
  public void send(Email mail);

  /**
   * @description: 发送富文本邮件
   * @method: send
   * @return: void
   * @date: 2018年03月09日 17:23:28
   * @author: wangfuming
   */
  public void sendHtml(Email mail);

  /**
   * @description: 发送模板邮件
   * @method: send
   * @return: void
   * @date: 2018年03月09日 17:23:28
   * @author: wangfuming
   */
  public void sendFreemarker(Email mail);
}
