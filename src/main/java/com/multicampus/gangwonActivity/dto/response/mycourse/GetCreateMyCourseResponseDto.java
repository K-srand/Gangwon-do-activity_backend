package com.multicampus.gangwonActivity.dto.response.mycourse;

import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class GetCreateMyCourseResponseDto extends ResponseDto {
    private Long myCourseNo;
    private LocalDateTime writtenTime;

    public GetCreateMyCourseResponseDto(Long myCourseNo, LocalDateTime writtenTime) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.myCourseNo = myCourseNo;
        this.writtenTime = writtenTime;
    }

    public static ResponseEntity<GetCreateMyCourseResponseDto> success(Long myCourseNo, LocalDateTime writtenTime) {
        GetCreateMyCourseResponseDto result = new GetCreateMyCourseResponseDto(myCourseNo, writtenTime);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> courseExists() {
        ResponseDto result = new ResponseDto(ResponseCode.COURSE_EXISTS, ResponseMessage.COURSE_EXISTS);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    public static ResponseEntity<ResponseDto> invalidUser() {
        ResponseDto result = new ResponseDto(ResponseCode.INVALID_USER, ResponseMessage.INVALID_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    public static ResponseEntity<ResponseDto> invalidCourseData() {
        ResponseDto result = new ResponseDto(ResponseCode.INVALID_COURSE_DATA, ResponseMessage.INVALID_COURSE_DATA);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
