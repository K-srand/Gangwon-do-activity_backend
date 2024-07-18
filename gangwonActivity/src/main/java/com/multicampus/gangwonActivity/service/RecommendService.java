package com.multicampus.gangwonActivity.service;

import com.multicampus.gangwonActivity.dto.response.recommend.BestMyCourseDto;

import java.util.List;
import java.util.Map;

public interface RecommendService {

    List<BestMyCourseDto> getBestInfo();

    List<Map<String, Object>> getCourseInfo(Long myCourseNo);

}
