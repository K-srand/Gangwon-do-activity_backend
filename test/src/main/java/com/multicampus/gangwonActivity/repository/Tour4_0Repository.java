package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.dto.request.GetPlaceTitleDto;
import com.multicampus.gangwonActivity.entity.Tour4_0Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;


public interface Tour4_0Repository extends JpaRepository<Tour4_0Entity, Long> {

}
