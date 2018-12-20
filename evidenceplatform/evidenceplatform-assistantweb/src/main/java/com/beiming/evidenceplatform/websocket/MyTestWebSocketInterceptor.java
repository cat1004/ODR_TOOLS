package com.beiming.evidenceplatform.websocket;
//package com.beiming.libra.websocket;
//
//import java.util.Map;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//public class MyTestWebSocketInterceptor implements HandshakeInterceptor {
//
//  @Override
//  public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
//      WebSocketHandler handler, Map<String, Object> map) throws Exception {
//    if (request instanceof ServletServerHttpRequest) {
//      ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
//      String userId = serverHttpRequest.getServletRequest().getParameter("userId");
//      map.put("userId", userId);
//
//    }
//    return true;
//
//  }
//
//
//  @Override
//  public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1,
//      WebSocketHandler arg2, Exception arg3) {
//
//  }
//
//
//
//}
