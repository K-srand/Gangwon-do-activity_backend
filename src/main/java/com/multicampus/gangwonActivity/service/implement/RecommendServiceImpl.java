package com.multicampus.gangwonActivity.service.implement;

import com.multicampus.gangwonActivity.dto.response.recommend.BestMyCourseDto;
import com.multicampus.gangwonActivity.mapper.RecommendMapper;
import com.multicampus.gangwonActivity.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService {
    private final RecommendMapper recommendMapper;


    @Override
    public List<BestMyCourseDto> getBestInfo() {
        return recommendMapper.getBestCourse();
    }

    @Override
    public List<Map<String,Object>> getCourseInfo(Long myCourseNo) {
        // placeTitle, firstImage2 정보
        return recommendMapper.getMyCourse(myCourseNo);
    }


}
