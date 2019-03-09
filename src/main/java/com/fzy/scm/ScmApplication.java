package com.fzy.scm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScmApplication {

    private static Logger logger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

    public static void main(String[] args) {
        SpringApplication.run(ScmApplication.class, args);
        logger.info("--启动完成--");
    }

}
