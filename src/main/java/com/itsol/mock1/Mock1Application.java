package com.itsol.mock1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableSpringDataWebSupport
@SpringBootApplication

public class Mock1Application {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Mock1Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Mock1Application.class, args);
    }

}
