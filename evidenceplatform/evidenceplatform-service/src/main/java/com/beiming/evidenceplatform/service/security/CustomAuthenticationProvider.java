package com.beiming.evidenceplatform.service.security;
//package com.beiming.jud.service.security;
//
//import com.beiming.jud.service.security.impl.CustomUserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import com.beiming.jud.service.security.entity.CustomUser;
//import com.beiming.jud.service.security.entity.TempUser;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Collection;
//import org.springframework.stereotype.Component;
//
///**
// * Created by xiaoqiang on 2018/6/22.
// * 自定义的认证里拿到MyWebAuthenticationDetails，从而拿到request
// */
//@Component
//@Configuration
//@ComponentScan({"com.beiming.jud.service.security"})
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//  @Autowired
//  private CustomUserServiceImpl customUserService;
//  @Autowired
//  private JwtTokenService jwtTokenService;
//  @Autowired
//  HttpServletRequest request;
//
//
//  ////第一次登陆和非第一次登陆在这里处理token，如果token不存在则查询数据库，如果存在则效验token，效验成功则更新token时效，失败则抛出异常
//  @Override
//  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//
//    Collection<? extends GrantedAuthority> authorities;
//    String token = null;
//    MyWebAuthenticationDetails myDetails;
//    TempUser user = new TempUser();
//
//    String userName = request.getParameter("username");
//    String passWord = request.getParameter("password");
//    String userId = request.getParameter("userId");
//    userId="1";
//    System.out.println(userName+"--"+passWord+"---"+userId);
//    
//    HttpSession session = request.getSession();
//
//    if (request.getParameter("token") == null) {
//      //数据库查询得到用户数据
//      
//      CustomUser jwtUser = customUserService.loadUserByUsername(userId);
//
//      if (jwtUser == null || !jwtUser.getUsername().equalsIgnoreCase(userName) || !passWord
//          .equals(jwtUser.getPassword())) {
//        //throw new BadCredentialsException("用户名或密码错误");
//      }
//
//      token = jwtTokenService.jwtGenerateToken("", "", "");
//      System.out.println("token："+token);
//      authorities = jwtUser.getAuthorities();
//    } else {
//      //效验token，并更新过期时间，注销等操作
//      token = jwtTokenService.jwtTokenCheck("", "", "", authentication.getCredentials());
//      System.out.println("token Check："+token);
//      authorities = authentication.getAuthorities();
//    }
//
//    return new UsernamePasswordAuthenticationToken(userName, token, authorities);
//  }
//
//  @Override
//  public boolean supports(Class<?> arg0) {
//    return true;
//  }
//
//
//}
