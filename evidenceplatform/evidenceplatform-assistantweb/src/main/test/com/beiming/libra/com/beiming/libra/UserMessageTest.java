package com.beiming.libra;


import com.beiming.libra.domain.UserMessage;
import com.beiming.libra.service.UserMessageService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: tyrion
 * @Date: 2018/6/5 14:01
 * @类描述: 用户消息测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMessageTest {

  @Autowired
  UserMessageService userMessageService;

  @Test
  public void test() {
//    UserMessage userMessage = userMessageService.getMessageById("1");
    boolean isOpen = false;
    assert isOpen = true; //如果开启了断言，会将isOpen的值改为true
    System.out.println("==================>" + isOpen); //打印是否开启了断言
//    assertThat(userMessage);
//    Assert.assertEquals("1111","222");
//    Assert.assertNotNull(userMessage);
//    assert userMessage!=null:"数据不为空";
    UserMessage um = new UserMessage();
    um.setReceiverId("1");
    List<UserMessage> list = userMessageService.listWithCondition(um);
    System.out.println("list.size()----" + list.size());
    //assert list.size()==0:"数据不为空,条数为："+list.size();
//    userMessageService.UpdateStatusById("1");

  }


}
