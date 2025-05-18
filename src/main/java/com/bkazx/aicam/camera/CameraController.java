package com.bkazx.aicam.camera;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

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
}
