package com.multicampus.gangwonActivity.dto.response.report;

import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportListResponseDto extends ResponseDto {
    private Long reportedContentNo;
    private Long boardNo;
    private Long commentNo;
    private LocalDateTime reportedTime;
    private Long userNo;
    private String content;
    private String userId;
    private LocalDateTime censoredTime;

    private ReportListResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }
    public ReportListResponseDto(String code, String message) {
        super(code, message);
    }

    public static ResponseEntity<ReportListResponseDto> success(){
        ReportListResponseDto result = new ReportListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
