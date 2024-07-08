package com.multicampus.gangwonActivity.service;

import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyFavoritesDto;
import com.multicampus.gangwonActivity.entity.MyFavoritesEntity;
import com.multicampus.gangwonActivity.mapper.CreateMyCourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateMyCourseService {

    private final CreateMyCourseMapper createMyCourseMapper;

    public List<MyFavoritesEntity> getPlaceCat(GetMyFavoritesDto getMyFavoritesDto) {
        String cat2 = getMyFavoritesDto.getPlaceCat();
        List<MyFavoritesEntity> results = new ArrayList<>();

        if(cat2.equals("activity")) {
            results.addAll(createMyCourseMapper.selectMyFavoritesActivity());
        }
        if(cat2.equals("restaurant")) {
            results.addAll(createMyCourseMapper.selectMyFavoritesRestaurant());
        }
        if(cat2.equals("cafe")) {
            results.addAll(createMyCourseMapper.selectMyFavoritesCafe());
        }
        if(cat2.equals("tour")) {
            results.addAll(createMyCourseMapper.selectMyFavoritesTour());
        }
        if(cat2.equals("accommodation")) {
            results.addAll(createMyCourseMapper.selectMyFavoritesAccommodation());
        }
        System.out.println(results);
        return results;
    }
}
