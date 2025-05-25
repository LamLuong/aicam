package com.bkazx.aicam.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.Valid;

import java.util.Map;

import com.bkazx.aicam.dto.request.UserSignupRequestDTO;
import com.bkazx.aicam.dto.response.MessageResponse;

@RestController
@RequestMapping(value = "/aicam/v1/user")
public class UserController {
  @Autowired
  private UserService userService;

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
}
