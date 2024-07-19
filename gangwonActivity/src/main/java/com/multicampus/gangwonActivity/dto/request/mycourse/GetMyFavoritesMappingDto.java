package com.multicampus.gangwonActivity.dto.request.mycourse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetMyFavoritesMappingDto {
    private Long placeNo;
    private String userId;
}
