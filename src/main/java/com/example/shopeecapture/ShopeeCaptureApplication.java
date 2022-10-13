package com.example.shopeecapture;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.shopeecapture.Mapper")
@EnableScheduling
@EnableAsync
public class ShopeeCaptureApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeeCaptureApplication.class, args);
    }

}
