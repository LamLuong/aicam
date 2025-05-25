package com.bkazx.aicam.camera;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/aicam/v1")
public class CameraController {
  @Autowired
  private final CameraRepository cameraRepository;

  public CameraController(@Autowired CameraRepository cameraRepository) {
    this.cameraRepository = cameraRepository;
  }

  @GetMapping(path = "/cameras")
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
