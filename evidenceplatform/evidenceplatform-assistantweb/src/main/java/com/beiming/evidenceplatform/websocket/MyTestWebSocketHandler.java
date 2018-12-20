package com.beiming.evidenceplatform.websocket;
//package com.beiming.libra.websocket;
//
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.CopyOnWriteArrayList;
//import javax.annotation.Resource;
//import org.springframework.stereotype.Service;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//import com.beiming.libra.security.TokenGenerator;
//
//@Service
//public class MyTestWebSocketHandler extends TextWebSocketHandler {
//  // 在线用户列表
//  private static final Map<Integer, WebSocketSession> USERS;
//
//  @Resource
//  private TokenGenerator tokenGenerator;
//
//  /**
//   * 所有的对象
//   */
//  public static List<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();
//
//  // 用户标识
//  private static final String CLIENT_ID = "userId";
//
//  static {
//    USERS = new ConcurrentHashMap<>();
//  }
//
//
//  @Override
//  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//    super.handleTextMessage(session, message);
//    TextMessage returnMessage = new TextMessage(message.getPayload() + " received at server");
//    // session.sendMessage(returnMessage);
//
//    for (WebSocketSession webSocketSession : sessions) {
//      webSocketSession.sendMessage(returnMessage);
//    }
//  }
//
//
//  @Override
//  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//    System.out.println("成功建立连接");
//    Integer userId = getClientId(session);
//    this.sessions.add(session);
//    System.out.println(userId);
//    if (userId != null) {
//      USERS.put(userId, session);
//      session.sendMessage(new TextMessage("成功建立socket连接"));
//      System.out.println(userId);
//      System.out.println(session);
//    }
//  }
//
//  @Override
//  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//    if (session.isOpen()) {
//      session.close();
//    }
//    System.out.println("连接出错");
//    USERS.remove(getClientId(session));
//  }
//
//  @Override
//  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//    System.out.println("连接已关闭：" + status);
//    USERS.remove(getClientId(session));
//    sessions.remove(session);
//  }
//
//  @Override
//  public boolean supportsPartialMessages() {
//    return false;
//  }
//
//  /**
//   * 获取用户标识
//   *
//   * @param session
//   * @return
//   */
//  private Integer getClientId(WebSocketSession session) {
//    try {
//      //session.getUri().get
//      Integer clientId = Integer.valueOf(session.getAttributes().get(CLIENT_ID).toString());
//      return clientId;
//    } catch (Exception e) {
//      return null;
//    }
//  }
//}
