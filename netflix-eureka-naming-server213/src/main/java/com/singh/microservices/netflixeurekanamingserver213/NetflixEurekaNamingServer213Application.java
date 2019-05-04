package com.singh.microservices.netflixeurekanamingserver213;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NetflixEurekaNamingServer213Application {

	public static void main(String[] args) {
		SpringApplication.run(NetflixEurekaNamingServer213Application.class, args);
	}

}
