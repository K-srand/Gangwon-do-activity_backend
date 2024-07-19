package com.multicampus.gangwonActivity.dto.response.board;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class GetBoardDetailResponseDto {
    private Long boardNo;
    private Long userNo;
    private String boardTitle;
    private String content;
    private int countLikes;
    private LocalDateTime writtenTime;
    private LocalDateTime deletedTime;
    private boolean isReported;
    private Long myCourseNo;
    private List firstImage2;
    private int boardCount;
    private String userNick;
    private List<GetBoardCommentListResponseDto> comments;
    private Integer userExp;
}
