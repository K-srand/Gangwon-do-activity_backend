package com.multicampus.gangwonActivity.service.implement;

import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyFavoritesMappingDto;
import com.multicampus.gangwonActivity.dto.response.mycourse.GetMyFavoritesResponseDto;
import com.multicampus.gangwonActivity.entity.MyFavoritesUserPlace;
import com.multicampus.gangwonActivity.mapper.MyFavoriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyFavoritesServiceImpl {

    private final MyFavoriteMapper myFavoriteMapper;

    // 찜
    public ResponseEntity<?> getMyFavorite(GetMyFavoritesMappingDto getMyFavoritesMappingDto) {
        Long userNo = myFavoriteMapper.selectUserNo(getMyFavoritesMappingDto.getUserId());

        boolean isFavoriteExists = myFavoriteMapper.selectExistsFavorite(userNo, getMyFavoritesMappingDto.getPlaceNo());
        if (isFavoriteExists) {
            return GetMyFavoritesResponseDto.favoriteExists();
        }

        MyFavoritesUserPlace myFavoritesUserPlace = MyFavoritesUserPlace.builder()
                .userNo(userNo)
                .placeNo(getMyFavoritesMappingDto.getPlaceNo())
                .build();
        myFavoriteMapper.saveFavorite(myFavoritesUserPlace);

        return GetMyFavoritesResponseDto.success();
    }
}
