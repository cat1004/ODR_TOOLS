package com.beiming.evidenceplatform.config;
//package com.beiming.libra.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//@EnableWebSocket
//public class WsConfigure implements WebSocketConfigurer {
//
//  @Override
//  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//    System.out.println("==========================");
//    registry.addHandler(myHandler(), "/accessMeeting2/{param}").setAllowedOrigins("*");
//  }
//
//  @Bean
//  public WsHandler myHandler() {
//    return new WsHandler();
//  }
//}