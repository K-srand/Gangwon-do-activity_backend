package com.multicampus.gangwonActivity.repository;

import com.multicampus.gangwonActivity.entity.MyCourseEntity;
import com.multicampus.gangwonActivity.entity.MyCourseMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreateMyCourseRepository extends JpaRepository<MyCourseMappingEntity, Long> {

}
