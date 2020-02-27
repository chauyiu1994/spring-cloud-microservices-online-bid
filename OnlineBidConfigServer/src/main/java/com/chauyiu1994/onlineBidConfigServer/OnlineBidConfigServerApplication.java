package com.chauyiu1994.onlineBidConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class OnlineBidConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBidConfigServerApplication.class, args);
	}

}
