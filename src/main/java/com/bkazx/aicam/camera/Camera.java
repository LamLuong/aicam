package com.bkazx.aicam.camera;

import java.sql.Timestamp;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bkazx.aicam.user.User;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Camera {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private String location;

  @ManyToOne(optional = false) // This makes user_id NOT NULL
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "create_at")
  private Timestamp createdAt;
}
