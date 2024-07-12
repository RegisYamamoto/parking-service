package com.regis.parking_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.SystemProperties;
import org.springframework.core.SpringVersion;

@Slf4j
@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Swagger OpenApi", version = "1", description = "Sistema de controle de vagas de estacionamento"))
public class ParkingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingServiceApplication.class, args);
		log.info("Spring version: {}", SpringVersion.getVersion());
		log.info("JDK version: {}", SystemProperties.get("java.version"));
	}

}
