package com.mx.kubernates.hm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	
	@GetMapping("/hola")
	public String holaMundo() {
		return "Hola mundo";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
