package com.multicampus.gangwonActivity.dto.request.mycourse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class GetMyCourseMappingDto {
    private Long placeNo;
    private Integer orderNo;

    @Override
    public String toString() {
        return "{" + placeNo + "," + orderNo +
                '}';
    }
}
