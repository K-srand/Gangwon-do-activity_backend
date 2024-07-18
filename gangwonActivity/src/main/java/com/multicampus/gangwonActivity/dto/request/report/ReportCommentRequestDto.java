package com.multicampus.gangwonActivity.dto.request.report;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportCommentRequestDto {

    private LocalDateTime reportedTime;
    private Long commentNo;

}
