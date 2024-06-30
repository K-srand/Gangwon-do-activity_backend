package com.multicampus.ganwonActivity;

//import com.multicampus.ganwonActivity.service.implement.PlaceService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
//import org.springframework.web.client.RestTemplate;


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
