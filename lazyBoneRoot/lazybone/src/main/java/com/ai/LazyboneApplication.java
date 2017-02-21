package com.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LazyboneApplication {

	public static void main(String[] args) {
//		System.setProperty("spring.profiles.active","dev");
		SpringApplication.run(LazyboneApplication.class, args);
	}
}
