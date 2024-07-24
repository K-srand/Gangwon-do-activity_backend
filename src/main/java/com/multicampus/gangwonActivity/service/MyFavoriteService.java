package com.multicampus.gangwonActivity.service;

import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyFavoritesMappingDto;


public interface MyFavoriteService {

    //찜
    String getMyFavorite(GetMyFavoritesMappingDto getMyFavoritesMappingDto);
}
