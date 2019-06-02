package com.fzy.pms;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PmsApplication.class, args);
        log.debug("----------启动完成----------");
    }
}
