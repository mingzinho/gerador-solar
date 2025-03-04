package com.neosolar.gerador_solar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GeradorSolarApplication {
	public static void main(String[] args) {
		SpringApplication.run(GeradorSolarApplication.class, args);
	}
}
