package com.multicampus.gangwonActivity.dto.response.report;

import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.MyPageResponseDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@RequiredArgsConstructor
public class ReportedContentResponseDto extends ResponseDto {


    private ReportedContentResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public ReportedContentResponseDto(String code, String message) {
        super(code, message);

    }

    public static ResponseEntity<ReportedContentResponseDto> success(){
        ReportedContentResponseDto result = new ReportedContentResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ReportedContentResponseDto> notExistContent(){
        ReportedContentResponseDto result = new ReportedContentResponseDto(ResponseCode.NOT_EXISTED_CONTENT, ResponseMessage.NOT_EXISTED_CONTENT);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(result);
    }

    public static ResponseEntity<ReportedContentResponseDto> alreadyReportedContent(){
        ReportedContentResponseDto result = new ReportedContentResponseDto(ResponseCode.ALREADY_REPORTED_CONTENT, ResponseMessage.ALREADY_REPORTED_CONTENT);
        return  ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(result);
    }


}
