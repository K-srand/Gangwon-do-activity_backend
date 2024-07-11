package com.multicampus.gangwonActivity.dto.request.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetPlaceTitleDto {
    private String placeTitle;
    private double placeMapx;
    private double placeMapy;

}
