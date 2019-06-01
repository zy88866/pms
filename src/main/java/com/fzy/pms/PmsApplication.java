package com.fzy.pms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PmsApplication {

    private static final Logger log = LoggerFactory.getLogger(PmsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PmsApplication.class, args);
        log.info("--启动完成--");
    }
}
