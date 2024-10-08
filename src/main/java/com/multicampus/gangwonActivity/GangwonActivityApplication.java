package com.multicampus.gangwonActivity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.multicampus.gangwonActivity.mapper")
public class GangwonActivityApplication {
	public static void main(String[] args) {
		SpringApplication.run(GangwonActivityApplication.class, args);
	}
}
