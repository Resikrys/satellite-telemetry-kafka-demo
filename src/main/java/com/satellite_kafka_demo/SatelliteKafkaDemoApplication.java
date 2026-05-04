package com.satellite_kafka_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SatelliteKafkaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SatelliteKafkaDemoApplication.class, args);
	}

}
