package com.backend.backend.RegisterAndLoginBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RegisterAndLoginBackend {

	public static void main(String[] args) {
		SpringApplication.run(RegisterAndLoginBackend.class, args);
	}

}
