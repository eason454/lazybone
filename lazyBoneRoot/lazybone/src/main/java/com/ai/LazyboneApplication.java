package com.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LazyboneApplication {

	public static void main(String[] args) {
//		System.setProperty("spring.profiles.active","default");
		SpringApplication.run(LazyboneApplication.class, args);
	}
}
