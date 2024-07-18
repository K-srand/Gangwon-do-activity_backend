package com.multicampus.gangwonActivity.controller;

import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyFavoritesMappingDto;
import com.multicampus.gangwonActivity.service.MyFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/getmyfavorite")
@RestController
@RequiredArgsConstructor
public class MyFavoritesController {

    private final MyFavoriteService myFavoriteService;

    //찜 리스트 가져오기 서비스 호출
    @PostMapping("")
    public String getMyFavorite(@RequestBody GetMyFavoritesMappingDto getMyFavoritesMappingDto) {
        return myFavoriteService.getMyFavorite(getMyFavoritesMappingDto);
    }
}