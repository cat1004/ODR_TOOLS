package com.beiming.evidenceplatform.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.beiming.evidenceplatform.domain.Message;

import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MessageMapper extends Mapper<Message> {

  int insertMessageWithUserId(@Param("msg") String msg, @Param("userId") long userId);

  int updateIsReaderByMsgId(@Param("msgId") int msgId, @Param("isReader") int isReader);

  List<Message> selectByPage(@Param("message") Message message, @Param("userId") String userId);

  Message queryMessageById(@Param("msgId") long msgId);

  int deleteMessages(@Param("msgIds") List<String> msgIds);
}
