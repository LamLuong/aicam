package com.bkazx.aicam.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bkazx.aicam.dto.request.LoginRequestDTO;
import com.bkazx.aicam.dto.request.UserSignupRequestDTO;
import com.bkazx.aicam.dto.response.LoginResponseDTO;
import com.bkazx.aicam.security.jwt.JwtUtils;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/aicam/v1/user")
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtUtils jwtUtils;
  
  @PutMapping
  @Operation(summary = "Signup new user")
  public @ResponseBody ResponseEntity<Map<String, String>> signupUser(
    @Valid @RequestBody UserSignupRequestDTO requestDTO) {

    User newUser = null;
    try {
      newUser = userService.signupUser(requestDTO);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
    }
    return ResponseEntity.status(HttpStatus.CREATED)
           .body(Map.of("id", String.valueOf(newUser.getId()), "name", newUser.getUsername(),
                 "email", newUser.getEmail()));
   }

  @PostMapping
  @Operation(summary = "login")
  public @ResponseBody ResponseEntity<LoginResponseDTO> login(
    @Valid @RequestBody LoginRequestDTO loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtUtils.generateJwtToken(authentication);

    return ResponseEntity.status(HttpStatus.CREATED)
           .body(LoginResponseDTO.builder()
           .username(loginRequest.getUsername())
           .token(token)
           .build());
  }
}
