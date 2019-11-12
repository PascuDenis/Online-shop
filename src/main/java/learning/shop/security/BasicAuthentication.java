//package ro.msg.learning.shop.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import ro.msg.learning.shop.service.CustomerDetailService;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
//@ConditionalOnProperty(name = "security", havingValue = "basic")
//public class BasicAuthentication extends WebSecurityConfigurerAdapter {
//
//  private final CustomerDetailService userDetailsService;
//  private final MyBasicAuthenticationEntryPoint authenticationEntryPoint;
//
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests()
//      .anyRequest().authenticated()
//      .and()
//      .httpBasic().authenticationEntryPoint(authenticationEntryPoint);
//  }
//
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//  }
//
//  @Bean
//  public BCryptPasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//}
