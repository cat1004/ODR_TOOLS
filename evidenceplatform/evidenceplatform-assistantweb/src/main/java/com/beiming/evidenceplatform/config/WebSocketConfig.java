package com.beiming.evidenceplatform.config;
//package com.beiming.libra.config;
//
//import java.security.Principal;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
//import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
//import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;
//
//@Configuration
//@EnableWebSocketMessageBroker
//@Slf4j
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//  @Override
//  public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
//    stompEndpointRegistry.addEndpoint("/endpointSang").withSockJS();
//    stompEndpointRegistry.addEndpoint("/testChat").withSockJS();
//    stompEndpointRegistry.addEndpoint("/endpointChat").withSockJS();
//    stompEndpointRegistry.addEndpoint("/accessMeeting").withSockJS();
//  }
//
//  @Override
//  public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
//
//    registry.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
//
//      @Override
//      public WebSocketHandler decorate(WebSocketHandler handler) {
//        return new WebSocketHandlerDecorator(handler) {
//
//          @Override
//          public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//            String userName = session.getPrincipal().getName();
//            log.info("online: " + userName);
//            super.afterConnectionEstablished(session);
//          }
//
//          @Override
//          public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
//              throws Exception {
//            String userName = session.getPrincipal().getName();
//            log.info("offline: " + userName);
//            super.afterConnectionClosed(session, closeStatus);
//          }
//        };
//      }
//    });
//
//  }
//
//
//  @Override
//  public void configureMessageBroker(MessageBrokerRegistry registry) {
//    registry.enableSimpleBroker("/topic", "/queue");
//  }
//}