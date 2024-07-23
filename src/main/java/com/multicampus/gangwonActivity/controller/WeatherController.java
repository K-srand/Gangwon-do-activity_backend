package com.multicampus.gangwonActivity.controller;


import com.multicampus.gangwonActivity.entity.Weather;
import com.multicampus.gangwonActivity.repository.WeatherRepository;
import com.multicampus.gangwonActivity.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/weather")
public class WeatherController {
    private final WeatherService weatherService;
    private final WeatherRepository weatherRepository;

    //날씨 API 저장 서비스 호출
    @Scheduled(cron = "0 0 6 * * *")
    @GetMapping("")
    public String getWeatherInDataBase() throws IOException,InterruptedException{


        return weatherService.save();
    }

    //날씨 데이터 가져오기 서비스 호출
    @GetMapping("/data")
    public List<Map<String, Object>> getWeatherData() {
        Weather weather = weatherRepository.findTopByOrderByWeatherNoDesc();

        List<Map<String, Object>> weatherData = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Map<String, Object> dayData = new HashMap<>();
            dayData.put("date", LocalDate.now().plusDays(i - 1).toString());
            dayData.put("taMax", weather.getTaMax(i));
            dayData.put("taMin", weather.getTaMin(i));
            dayData.put("rnSt", weather.getRnSt(i));
            dayData.put("weather", weather.getWf(i));
            weatherData.add(dayData);
        }

        return weatherData;
    }

}
