package com.example.teams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TeamMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamMsApplication.class, args);
	}

}
