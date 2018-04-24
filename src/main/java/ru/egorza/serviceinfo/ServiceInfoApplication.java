package ru.egorza.serviceinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceInfoApplication.class, args);
	}
}
