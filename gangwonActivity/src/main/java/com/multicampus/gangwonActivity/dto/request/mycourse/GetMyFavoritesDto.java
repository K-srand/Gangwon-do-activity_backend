package com.multicampus.gangwonActivity.dto.request.mycourse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetMyFavoritesDto {
    private String userId;
    private String placeCat;
}
