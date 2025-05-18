package com.bkazx.aicam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bkazx.aicam.user.User;
import com.bkazx.aicam.camera.Camera;
import com.bkazx.aicam.user.UserRepository;
import com.bkazx.aicam.camera.CameraRepository;
import java.sql.Timestamp;

@SpringBootApplication
public class AicamApplication {

	public static void main(String[] args) {
		SpringApplication.run(AicamApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
		UserRepository userRepository,
		CameraRepository cameraRepository
	) {
		return args -> {

        System.out.println("Hello from CommandLineRunner!");
				// User user = User.builder()
				//  						.username("user1")
				// 						.email("email@test.com")
				// 						.password("123456")
				// 						.build();
				// userRepository.save(user);

				// for (int i = 0; i < 20; i++) {
				// 	Camera cam = Camera.builder()
				// 							.name("camera1")
				// 							.location("S1")
				// 							.user(user)
				// 							.createdAt(new Timestamp(System.currentTimeMillis()))
				// 							.build();
				// 	cameraRepository.save(cam);
				// }
		};
	}
}
