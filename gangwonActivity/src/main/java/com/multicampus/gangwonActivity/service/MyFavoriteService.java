package com.multicampus.gangwonActivity.service;

import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyFavoritesMappingDto;
import com.multicampus.gangwonActivity.entity.MyFavoritesEntity;
import com.multicampus.gangwonActivity.mapper.MyFavoriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyFavoriteService {

    private final MyFavoriteMapper myFavoriteMapper;

    //찜
    public String getMyFavorite(GetMyFavoritesMappingDto getMyFavoritesMappingDto) {
        Long userNo = myFavoriteMapper.selectUserNo(getMyFavoritesMappingDto.getUserId());

        MyFavoritesEntity myFavoritesEntity = MyFavoritesEntity.builder()
                .userNo(userNo)
                .placeNo(getMyFavoritesMappingDto.getPlaceNo())
                .build();
        myFavoriteMapper.saveFavorite(myFavoritesEntity);

        return "success favorite";
    }
}
