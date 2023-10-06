package com.fivengers.blooming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BloomingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloomingApplication.class, args);
    }

}
