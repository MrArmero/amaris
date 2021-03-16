package com.amaris.masa.inditex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class InditexApplication {

	public static void main(String[] args) {
		SpringApplication.run(InditexApplication.class, args);
	}

}
