package com.beiming.evidenceplatform.security;

import com.beiming.evidenceplatform.common.constants.SecurityConst;
import com.beiming.evidenceplatform.controller.DefaultErrorController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Resource
  private JWTAuthenticationFilter jwtAuthenticationFilter;
  @Resource
  private JWTAuthenticationProvider jwtAuthenticationProvider;

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(DefaultErrorController.ERROR_PATH,
        "/api/pc/assistant/areas/province", "/api/pc/assistant/areas/province/**",
        "/auth/assistant/permission/assistLogin", "/auth/assistant/permission/systemLogin",
        "/auth/assistant/permission/courtManageLogin", "/auth/assistant/permission/refreshToken",
        "/auth/assistant/permission/assistanterUserLogout",
        "/auth/assistant/permission/systemUserLogout",
        "/auth/assistant/permission/courtManageLogout",
        "/auth/assistant/permission/assistanterUserForgetPwd",
        "/auth/assistant/permission/getPhoneCode", "/api/pc/assistant/provinces",
        "/api/pc/assistant/citys/**", "/api/pc/assistant/userManagement/getCourts",
        "/account/login", "/account/register", "/account/forgetPwd", "/account/imgCode",
        "/account/imgCodeVerified", "/account/getPhoneCode", "/account/fromOtherPlatform",
        "/account/refreshToken", "/account/createUserFromOtherPlatform",
        "/auth/mobile/assistant/permission/assistLogin",
        "/auth/mobile/assistant/permission/systemLogin",
        "/auth/mobile/assistant/permission/courtManageLogin",
        "/auth/mobile/assistant/permission/assistanterUserForgetPwd",
        "/auth/mobile/assistant/permission/getPhoneCode", "/swagger-ui.html", "/webjars/**",
        "/swagger-resources/**", "/v2/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().cors().and().exceptionHandling()
        .authenticationEntryPoint(new JWTAuthenticationEntryPoint())
        .accessDeniedHandler(new JWTAccessDeniedHandler()).and()
        .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
        .authenticationProvider(jwtAuthenticationProvider).authorizeRequests()
        .antMatchers("/api/pc/assistant/auction/**").hasAnyAuthority(SecurityConst.ASSISTANT)
        .antMatchers("/api/pc/assistant/corpore/listBypage")
        .hasAnyAuthority(SecurityConst.ASSISTANT)
        .antMatchers("auth/mobile/assistant/permission/assistanterUser/**")
        .hasAnyAuthority(SecurityConst.ASSISTANT).antMatchers("/api/pc/assistant/key/auction/**")
        .hasAnyAuthority(SecurityConst.ASSISTANT)
        .antMatchers("/api/pc/assistant/orgnazation/getCourtsByAreaIdAndName")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/orgnazation/addCourt")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/orgnazation/getCourtById/**")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/orgnazation/deleteCourtById/**")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/orgnazation/modifyCourt")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/orgnazation/getOrgnazationList")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/userManagement/getJudges")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN, SecurityConst.ROLE_JUDGE_M)
        .antMatchers("/api/pc/assistant/userManagement/addJudge")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN, SecurityConst.ROLE_JUDGE_M)
        .antMatchers("/api/pc/assistant/userManagement/getJudgeById/**")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN, SecurityConst.ROLE_JUDGE_M)
        .antMatchers("/api/pc/assistant/userManagement/modifyJudgeOrCourtAdministratorPhone")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN, SecurityConst.ROLE_JUDGE_M)
        .antMatchers("/api/pc/assistant/userManagement/modifyJudgeOrCourtAdministratorPassword")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN, SecurityConst.ROLE_JUDGE_M)
        .antMatchers("/api/pc/assistant/userManagement/modifyJudgeStatus/**")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN, SecurityConst.ROLE_JUDGE_M)
        .antMatchers("/api/pc/assistant/userManagement/getAssistanters")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/userManagement/getAssistantersByName")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/userManagement/getAssistanter/**")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/userManagement/modifyAssistanterPhone")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/userManagement/modifyAssistanterPassword")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/userManagement/modifyAssistanterStatus/**")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/userManagement/addAssistanter")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/userManagement/addCourtAdministrator")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/userManagement/getCourtAdministrators")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/userManagement/getCourtAdministratorById/**")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN)
        .antMatchers("/api/pc/assistant/userManagement/modifyCourtAdministratorStatus/**")
        .hasAnyAuthority(SecurityConst.ROLE_SYSADMIN).antMatchers("/api/mobile/auction/**")
        .hasAnyAuthority(SecurityConst.ASSISTANT).antMatchers("/api/mobile/getOrgnazationList")
        .hasAnyAuthority(SecurityConst.ASSISTANT).antMatchers("/api/mobile/assisterUser/**")
        .hasAnyAuthority(SecurityConst.ASSISTANT).filterSecurityInterceptorOncePerRequest(true)
        .anyRequest().authenticated().and().logout().logoutUrl("/logout")
        .addLogoutHandler(new JWTLogoutHandler())
        .logoutSuccessHandler(new JWTLogoutSuccessHandler());
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("*"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}

