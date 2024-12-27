package com.bingoverse.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bingoverse.config")
public class BingoVerseConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(BingoVerseConfigApplication.class, args);
	}

}
