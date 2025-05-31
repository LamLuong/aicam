package com.bkazx.aicam.camera;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping(value = "/aicam/v1")
public class CameraController {
  @Autowired
  private final CameraRepository cameraRepository;

  public CameraController(@Autowired CameraRepository cameraRepository) {
    this.cameraRepository = cameraRepository;
  }

  @GetMapping(path = "/cameras")
  @Operation(summary = "Get all cameras", security = @SecurityRequirement(name = "bearerAuth"))
  public List<Camera> getListCameras() {
    return cameraRepository.findAll();

  }

  @DeleteMapping(path = "/camera/{camId}")
  public ResponseEntity<Void> deleteACamera(@PathVariable Integer camId) {
    Camera camera = null;
    Optional<Camera> cameraOptional = cameraRepository.findById(camId);
    
    if (cameraOptional.isPresent()) {
      this.cameraRepository.delete(cameraOptional.get());
      return ResponseEntity.ok().build();
    }

    return ResponseEntity.notFound().build();
  }
}
