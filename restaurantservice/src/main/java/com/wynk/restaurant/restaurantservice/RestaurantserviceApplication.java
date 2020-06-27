package com.wynk.restaurant.restaurantservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class RestaurantserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(RestaurantserviceApplication.class, args);
	}

}
