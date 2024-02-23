package com.rinha.ismael;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IsmaelApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsmaelApplication.class, args);
	}

}
