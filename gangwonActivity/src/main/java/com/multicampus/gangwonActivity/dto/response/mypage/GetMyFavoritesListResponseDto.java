package com.multicampus.gangwonActivity.dto.response.mypage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMyFavoritesListResponseDto {
    private Long placeNo;
    private String placeTitle;
    private String firstImage;
    private double rate;

}
