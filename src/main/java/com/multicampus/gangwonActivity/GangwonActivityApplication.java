package com.multicampus.gangwonActivity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.multicampus.gangwonActivity.mapper")
public class GangwonActivityApplication {
	public static void main(String[] args) {
		SpringApplication.run(GangwonActivityApplication.class, args);
	}
}
