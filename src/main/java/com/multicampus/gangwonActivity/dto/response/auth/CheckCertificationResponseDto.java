package com.multicampus.gangwonActivity.dto.response.auth;

import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class CheckCertificationResponseDto extends ResponseDto {
    private CheckCertificationResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<CheckCertificationResponseDto> success(){
        CheckCertificationResponseDto responsebody = new CheckCertificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responsebody);
    }

    public static ResponseEntity<ResponseDto> sessionNull(){
        ResponseDto responsebody = new ResponseDto(ResponseCode.SESSION_NULL,ResponseMessage.SESSION_NULL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responsebody);
    }


    public static ResponseEntity<ResponseDto> certificationFail(){
        ResponseDto responsebody = new ResponseDto(ResponseCode.NUMBER_ERROR,ResponseMessage.NUMBER_ERROR);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responsebody);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXITSTED_USER, ResponseMessage.NOT_EXITSTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
