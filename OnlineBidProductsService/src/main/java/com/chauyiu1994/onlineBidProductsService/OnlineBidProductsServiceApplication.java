package com.chauyiu1994.onlineBidProductsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.reactive.config.EnableWebFlux;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableWebFlux
@EnableDiscoveryClient
@SpringBootApplication
public class OnlineBidProductsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBidProductsServiceApplication.class, args);
	}

	@PostConstruct
	public void init(){
		// Setting Spring Boot SetTimeZone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
