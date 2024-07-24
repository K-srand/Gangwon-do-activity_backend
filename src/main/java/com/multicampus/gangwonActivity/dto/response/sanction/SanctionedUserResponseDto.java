package com.multicampus.gangwonActivity.dto.response.sanction;

import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SanctionedUserResponseDto extends ResponseDto {

    private SanctionedUserResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public SanctionedUserResponseDto(String code, String message) {
        super(code, message);
    }

    public static ResponseEntity<SanctionedUserResponseDto> success(){
        SanctionedUserResponseDto result = new SanctionedUserResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<SanctionedUserResponseDto> alreadySanctionedUser(){
        SanctionedUserResponseDto result = new SanctionedUserResponseDto(ResponseCode.ALREADY_SANCTIONED_USER, ResponseCode.ALREADY_SANCTIONED_USER);
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(result);
    }




}
