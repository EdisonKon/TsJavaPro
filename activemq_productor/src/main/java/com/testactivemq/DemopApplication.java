package com.testactivemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemopApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemopApplication.class, args);
    }

}
