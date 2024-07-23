package com.multicampus.gangwonActivity.dto.response.board;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetBoardListResponseDto {
    private int boardNo;
    private int userNo;
    private String boardTitle;
    private String content;
    private int countLikes;
    private LocalDateTime writtenTime;
    private LocalDateTime deletedTime;
    private Integer myCourseNo;
    private boolean isReported;
    private String userNick;
    private Integer boardCount;
    private Integer userExp;
    private LocalDateTime censoredTime;
}
