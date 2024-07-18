package com.multicampus.gangwonActivity.service;

import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyCourseDto;
import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyFavoritesDto;
import com.multicampus.gangwonActivity.entity.MyFavoritesUserPlace;

import java.util.List;

public interface CreateMyCourseService {

    // 카테고리별 찜 리스트
    List<MyFavoritesUserPlace> getPlaceCat(GetMyFavoritesDto getMyFavoritesDto);

    // 코스 저장
    String getMyCourse(GetMyCourseDto getMyCourseDto);

}
