package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.dto.response.recommend.BestMyCourseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecommendMapper {

    List<BestMyCourseDto> getBestCourse();

    List<Map<String,Object>> getMyCourse(@Param("myCourseNo") Long myCourseNo);
}
