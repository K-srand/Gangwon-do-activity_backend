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

@Bean
public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurer() {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedOrigins("http://3.36.27.202")
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 명시적 방법 설정
					.allowCredentials(true)
					.maxAge(3600);
		}
	};
 }
}
