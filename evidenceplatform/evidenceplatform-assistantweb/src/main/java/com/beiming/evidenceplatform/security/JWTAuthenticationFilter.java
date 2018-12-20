package com.beiming.evidenceplatform.security;

import com.alibaba.fastjson.JSONObject;
import com.beiming.evidenceplatform.common.AppException;
import com.beiming.evidenceplatform.common.constants.SecurityConst;
import com.beiming.evidenceplatform.helper.Result;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = request.getHeader(SecurityConst.JWT_AUTH_TOKEN_KEY_NAME);
    SecurityContextHolder.clearContext();
    if (StringUtils.isNotBlank(token)) {
      SecurityContextHolder.getContext().setAuthentication(new JWTAuthenticationToken(token));
    }
    try {
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      @SuppressWarnings("rawtypes")
      Result res = Result.failed(e);
      if (e instanceof AppException) {
        log.error("exception : {}", res.getMessage());
      } else {
        log.error("exception : ", e);
      }
      response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
      response.getWriter().write(JSONObject.toJSONString(res));
      response.flushBuffer();
    }
  }
}
