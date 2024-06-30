package com.multicampus.ganwonActivity.repository;

import com.multicampus.ganwonActivity.entity.PlaceEntity;
import com.multicampus.ganwonActivity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {

    PlaceEntity findByPlaceNo(Long placeNo);


}
