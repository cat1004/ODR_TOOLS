package com.beiming.evidenceplatform.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.beiming.evidenceplatform.common.page.Page;
import com.beiming.evidenceplatform.dao.MessageMapper;
import com.beiming.evidenceplatform.domain.Message;
import com.beiming.evidenceplatform.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 系统消息业务
 */
@Service
public class MessageServiceImpl implements MessageService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

  @Resource
  private MessageMapper messageMapper;

  public Map<String, Object> findItemByPage(Page page, Message message, String currentUserId) {
    LOGGER.info("传入的参数为:   " + page);
    LOGGER.info("传入的参数为:   " + message);
    PageHelper.startPage(page.getPageNo(), page.getPageSize());
    List<Message> list = messageMapper.selectByPage(message, currentUserId);
    // 3、获取分页查询后的数据
    PageInfo<Message> pageInfo = new PageInfo<>(list);
    Map<String, Object> map = new HashMap<>();
    map.put("totalPage", pageInfo.getLastPage());
    map.put("list", list);
    return map;
  }

  @Override
  public Message getMessageDetail(long msgId) {
    int effectRow = 0;
    effectRow = messageMapper.updateIsReaderByMsgId((int) msgId, 1);
    Message message = null;
    if (effectRow == 1) {
      message = messageMapper.queryMessageById(msgId);
    }
    return message;
  }

  @Override
  public int deleteMessages(String msgIds) {
    int effectRow = 0;
    List<String> list = getList(msgIds);
    effectRow = messageMapper.deleteMessages(list);
    return effectRow;
  }


  public List<String> getList(String id) {
    List<String> list = new ArrayList<String>();
    String[] str = id.split(",");
    for (int i = 0; i < str.length; i++) {
      list.add(str[i]);
    }
    return list;
  }

}
