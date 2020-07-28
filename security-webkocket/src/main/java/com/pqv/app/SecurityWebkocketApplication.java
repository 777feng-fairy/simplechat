package com.pqv.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.pqv.app.mapper")
@SpringBootApplication
public class SecurityWebkocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityWebkocketApplication.class, args);
	}

}
