package com.bkazx.aicam.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bkazx.aicam.dto.request.UserSignupRequestDTO;
import com.bkazx.aicam.exception.ValidationError;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final PasswordEncoder passwordEncoder;

  public User signupUser(UserSignupRequestDTO requestDTO) throws ValidationError {
    if (userRepository.existsByEmail(requestDTO.getEmail())) {
        throw new ValidationError(
            String.format("Email already exists: %s", requestDTO.getEmail()));
    }

    System.out.println(requestDTO.getUsername());

    User newUser = User.builder()
                .username(requestDTO.getUsername())
                .email(requestDTO.getEmail())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                // .role(requestDTO.getRole())
                .build();

    return userRepository.save(newUser);
}

} 
