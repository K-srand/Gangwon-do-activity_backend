package com.multicampus.gangwonActivity.dto.request.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyCourseUploadRequestDto {
    private String userId;
    private Long myCourseNo;
}
