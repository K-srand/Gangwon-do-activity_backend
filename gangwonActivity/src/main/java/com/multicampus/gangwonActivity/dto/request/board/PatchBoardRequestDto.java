package com.multicampus.gangwonActivity.dto.request.board;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchBoardRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private Long myCourseNo;





}
