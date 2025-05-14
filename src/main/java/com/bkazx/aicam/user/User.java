package com.bkazx.aicam.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
  @Id
  private int id;
  private String username;
  private String email;
  private String password;
}
