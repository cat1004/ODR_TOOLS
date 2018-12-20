package com.beiming.evidenceplatform.service;

import java.util.Map;

import com.beiming.evidenceplatform.common.page.Page;
import com.beiming.evidenceplatform.domain.Message;

/**
 * 类说明
 */
public interface MessageService {

  public Map<String, Object> findItemByPage(Page page, Message message, String currentUserId);

  public Message getMessageDetail(long msgId);

  public int deleteMessages(String msgIds);

}
