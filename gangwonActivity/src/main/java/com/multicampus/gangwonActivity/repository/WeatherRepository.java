package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    WeatherEntity findTopByOrderByWeatherNoDesc();
}
