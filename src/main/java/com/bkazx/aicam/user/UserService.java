package com.bkazx.aicam.user;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.bkazx.aicam.exception.ValidationError;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bkazx.aicam.dto.request.UserSignupRequestDTO;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(@Autowired UserRepository userRepository) {
      this.userRepository = userRepository;
      passwordEncoder = new BCryptPasswordEncoder();
  }

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
