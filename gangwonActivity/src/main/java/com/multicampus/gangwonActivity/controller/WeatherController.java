package com.multicampus.gangwonActivity.controller;


import com.multicampus.gangwonActivity.entity.WeatherEntity;
import com.multicampus.gangwonActivity.repository.WeatherRepository;
import com.multicampus.gangwonActivity.service.WeatherService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("")
    public String getWeatherInDataBase() throws IOException,InterruptedException{


        return weatherService.save();
    }

    @GetMapping("/data")
    public List<Map<String, Object>> getWeatherData() {
        WeatherEntity weatherEntity = weatherRepository.findTopByOrderByWeatherNoDesc();

        List<Map<String, Object>> weatherData = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Map<String, Object> dayData = new HashMap<>();
            dayData.put("date", LocalDate.now().plusDays(i - 1).toString());
            dayData.put("taMax", weatherEntity.getTaMax(i));
            dayData.put("taMin", weatherEntity.getTaMin(i));
            dayData.put("rnSt", weatherEntity.getRnSt(i));
            dayData.put("weather", weatherEntity.getWf(i));
            weatherData.add(dayData);
        }

        return weatherData;
    }

}
