package com.beiming.evidenceplatform.helper;

import com.beiming.evidenceplatform.common.Assert;
import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.security.JWTAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextUtil {

  public static String getCurrentUserId() {
    return getJWTAuthentication().getUserId();
  }

  public static String getCurrentUserName() {
    return getJWTAuthentication().getUserName();
  }

  public static JWTAuthenticationToken getJWTAuthentication() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Assert.isTrue(authentication != null && authentication.isAuthenticated()
        && authentication instanceof JWTAuthenticationToken, ErrorCode.USER_NOT_LOGIN);

    return (JWTAuthenticationToken) authentication;
  }

  // public static User getLoginUserInfo() {
  // JWTAuthenticationToken userJWT = getJWTAuthentication();
  // User loginUser = new User();
  // loginUser.setId(userJWT.getUserId());
  // loginUser.setLoginName(userJWT.getUserName());
  // return loginUser;
  // }
}
