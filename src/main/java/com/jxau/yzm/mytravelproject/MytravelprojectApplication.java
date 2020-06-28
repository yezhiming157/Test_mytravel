package com.jxau.yzm.mytravelproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MytravelprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MytravelprojectApplication.class, args);
        Logger logger = LoggerFactory.getLogger(MytravelprojectApplication.class);
        logger.debug("LOGGING DEBUG");

    }

}
