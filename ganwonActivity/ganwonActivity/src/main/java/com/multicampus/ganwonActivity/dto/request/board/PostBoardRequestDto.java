package com.multicampus.ganwonActivity.dto.request.board;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostBoardRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private Long myCourseNo;


//    이미지 관련된 아이들 넣어줘야함. -> 지금은 단순 커뮤니티글에 대한 자료만 넘겨 받을예정.
//    @NotNull
//    private List<String> boardImageList;

}
