package com.multicampus.gangwonActivity.dto.response.board;

import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetBoardCommentListResponseDto{
    private Long commentNo;
    private Long boardNo;
    private Long userNo;
    private LocalDateTime writtenTime;
    private String content;
    private String userNick;
    private Integer userExp;
    private String userRole;
}
