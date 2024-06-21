package com.multicampus.ganwonActivity.dto.response;

import com.multicampus.ganwonActivity.common.ResponseCode;
import com.multicampus.ganwonActivity.common.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String code;
    private String message;

    public static ResponseEntity<ResponseDto> databaseError(){
        ResponseDto respoonseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respoonseBody);

    }

    // 로그인 정보가 틀렸을때
    public static ResponseEntity<ResponseDto> validationFailed(){
        ResponseDto respoonseBody = new ResponseDto(ResponseCode.VALIDATION_FAILED, ResponseMessage.VALIDATION_FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respoonseBody);

    }

}
