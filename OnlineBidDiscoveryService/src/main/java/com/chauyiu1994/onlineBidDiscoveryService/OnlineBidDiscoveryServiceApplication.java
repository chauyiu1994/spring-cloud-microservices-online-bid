package com.chauyiu1994.onlineBidDiscoveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class OnlineBidDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBidDiscoveryServiceApplication.class, args);
	}

}
