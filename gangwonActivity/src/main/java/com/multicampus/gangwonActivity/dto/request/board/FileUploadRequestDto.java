package com.multicampus.gangwonActivity.dto.request.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileUploadRequestDto {

    private String fileName;
    private String fileUrl;

}
