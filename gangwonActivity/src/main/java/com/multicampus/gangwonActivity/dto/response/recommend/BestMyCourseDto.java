package com.multicampus.gangwonActivity.dto.response.recommend;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BestMyCourseDto {
    private Long boardNo;
    private Long userNo;
    private Long myCourseNo;
    private String userNick;
    private String placeTitle;
    private String firstImage2;
}
