package com.beiming.evidenceplatform.websocket;
//package com.beiming.libra.websocket;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@Configuration
//@EnableWebSocket
//public class MyWebSocketConfig implements WebSocketConfigurer {
//
//  @Override
//  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//    registry.addHandler(myTestWebSocketHandler(), "/myHandler")
//    .addInterceptors(new MyTestWebSocketInterceptor()).setAllowedOrigins("*")/*.withSockJS()*/;
//
//  }
//
//  @Bean
//  public WebSocketHandler myTestWebSocketHandler() {
//    return new MyTestWebSocketHandler();
//  }
//
//  /*@Bean
//  public ServerEndpointExporter serverEndpointExporter(){
//      return new ServerEndpointExporter();
//  }*/
//
//}
//
