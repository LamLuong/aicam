package com.bkazx.aicam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bkazx.aicam.security.services.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
class WebSecurityConfig {

  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      // .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
      // .authorizeHttpRequests(d -> d.requestMatchers(HttpMethod.GET, "/**").permitAll())
      .authorizeHttpRequests(d -> d.requestMatchers(HttpMethod.GET, "/aicam/v1/cameras").authenticated())
      .authorizeHttpRequests(d -> d.requestMatchers(HttpMethod.POST, "/aicam/v1/user/**").permitAll())
      .authorizeHttpRequests(d -> d.requestMatchers(HttpMethod.PUT, "/aicam/v1/user/**").permitAll())
      .authorizeHttpRequests(d -> d.requestMatchers(HttpMethod.GET, "/swagger-ui.html", "/swagger-ui/**", "/api-docs/**").permitAll())
      // .authorizeHttpRequests(d -> d.requestMatchers(HttpMethod.GET, "/lookup/**").permitAll())
      // .authorizeHttpRequests(
      //       d -> d.requestMatchers(HttpMethod.POST, "/stocks/**").hasRole(Roles.EMPLOYEE.getRole()))
      // .authorizeHttpRequests(
      //       d -> d.requestMatchers(HttpMethod.PUT, "/stocks/**").hasRole(Roles.EMPLOYEE.getRole()))
      // .authorizeHttpRequests(
      //       d -> d.requestMatchers(HttpMethod.DELETE, "/stocks/**").hasRole(Roles.EMPLOYEE.getRole()))
      // .authorizeHttpRequests(d -> d.requestMatchers(HttpMethod.GET, "/stocks/**").permitAll())
      // .authorizeHttpRequests(d -> d.requestMatchers("/carts/**").hasRole(Roles.CLIENT.getRole()))
      // .authorizeHttpRequests(d -> d.requestMatchers("/orders/**").hasRole(Roles.CLIENT.getRole()))
      // .authorizeHttpRequests(d -> d.anyRequest().authenticated())
      ;
      return http.build();
   }
}
