package com.multicampus.gangwonActivity;

import com.multicampus.gangwonActivity.dto.request.board.PostBoardRequestDto;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication

@MapperScan("com.multicampus.gangwonActivity.mapper")
public class GangwonActivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(GangwonActivityApplication.class, args);



	}

@Bean
public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurer() {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/api/*")
					.allowedOrigins("http://localhost:3000")
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
					.allowedHeaders("*")
					.allowCredentials(true)
					.maxAge(3600);
		}
	};
 }
}
