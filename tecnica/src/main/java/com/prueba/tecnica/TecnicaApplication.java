package com.prueba.tecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class TecnicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TecnicaApplication.class, args);
	}

}
