package com.solodoukhin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author: Solodoukhin Viktor
 * Date: 14.07.18
 * Description: main class need for starting spring boot project
 */
@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("-------------------------------------------------------");
        SpringApplication.run(Application.class, args);
    }
}
