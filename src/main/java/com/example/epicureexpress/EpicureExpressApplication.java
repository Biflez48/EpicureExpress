package com.example.epicureexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class EpicureExpressApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpicureExpressApplication.class, args);
    }

}
