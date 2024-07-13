package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Weather findTopByOrderByWeatherNoDesc();
}
