package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.MyCoursePlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateMyCourseRepository extends JpaRepository<MyCoursePlace, Long> {

}
