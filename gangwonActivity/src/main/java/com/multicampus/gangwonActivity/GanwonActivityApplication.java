package com.multicampus.gangwonActivity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GanwonActivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(GanwonActivityApplication.class, args);
	}
//	@Bean
//	public RestTemplate restTemplate() {
//		RestTemplate restTemplate = new RestTemplate();
//		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//		messageConverters.add(new MappingJackson2HttpMessageConverter());
//		messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
//		restTemplate.setMessageConverters(messageConverters);
//		return restTemplate;
//	}
}
