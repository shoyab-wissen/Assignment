package com.database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.database")
public class MultipleDatasourcesApplication {

    public static void main(String[] args) {
		SpringApplication.run(MultipleDatasourcesApplication.class, args);

	}
}
