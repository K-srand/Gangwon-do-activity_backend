package com.multicampus.gangwonActivity.dto.request.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetNaverMapDto {
    private Double startLat;
    private Double startLng;
    private Double endLat;
    private Double endLng;
}
