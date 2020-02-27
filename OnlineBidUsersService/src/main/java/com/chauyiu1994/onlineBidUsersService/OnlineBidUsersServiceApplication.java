package com.chauyiu1994.onlineBidUsersService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableReactiveMongoRepositories
@EnableReactiveFeignClients
//@EnableFeignClients
// enabled the CircuitBreaker pattern using the default setting and custom fallback method
//@EnableCircuitBreaker
public class OnlineBidUsersServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(OnlineBidUsersServiceApplication.class, args);
	}
}
