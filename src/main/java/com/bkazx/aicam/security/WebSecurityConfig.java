package com.bkazx.aicam.security;

import com.bkazx.aicam.user.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
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
