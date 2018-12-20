package com.beiming.evidenceplatform.service.security.config;

//import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by xiaoqiang on 2018/6/23.1
 */
//@EnableWebSecurity
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    /*auth
        .inMemoryAuthentication()
        .withUser("user").password("password").roles("USER");
    String path = GetResource.class.getClassLoader().getResource("security/success.html").getPath();
    String relativelyPath = System.getProperty("user.dir");
    System.out.printf("++++=++++++++++++++++++" + path);
    System.out.printf("++++=++++++++++++++++++" + relativelyPath);*/
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/user/success", "user/admin").permitAll()//配置不要权限验证的页面和跳转
//        .anyRequest().authenticated()//设置其他所有路径都需要权限校验
        .and()
        .csrf().disable()//关闭csrf,还不清楚原因
        .formLogin()  //注册 UsernamePasswordAuthenticationFilter，对用户名密码进行效验，
        //会通过request获得表单中的用户名密码默认是username和password。
        .loginPage("/user/access") //表单登录页面地址
        .loginProcessingUrl("/security/login")//form表单POST请求url提交地址，后面试着换成control看行不行
        .passwordParameter("password")//form表单用户名参数名
        .usernameParameter("username") //form表单密码参数名
        //.successForwardUrl("/success.html")  //登录成功跳转地址
        //.failureForwardUrl("/error.html") //登录失败跳转地址，貌似这个版本去掉了这两个方法
        .defaultSuccessUrl("/security/success.html")//如果用户没有访问受保护的页面，默认跳转到页面
        .failureUrl("/security/error.html")
        //.failureHandler(AuthenticationFailureHandler)//handler对象取不到
        //.successHandler(AuthenticationSuccessHandler)
        .failureUrl("/security/login?error")//加个区分
        .permitAll(); //允许所有用户都有权限访问登录页面
  }
}
