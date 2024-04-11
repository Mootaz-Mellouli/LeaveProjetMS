package com.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EventMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventMsApplication.class, args);
    }


}
