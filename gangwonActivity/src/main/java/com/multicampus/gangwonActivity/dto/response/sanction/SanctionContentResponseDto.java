package com.multicampus.gangwonActivity.dto.response.sanction;

import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SanctionContentResponseDto extends ResponseDto {

    private SanctionContentResponseDto() {super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);}

    public  SanctionContentResponseDto(String code, String message) {super(code, message);}

    public static ResponseEntity<SanctionContentResponseDto> success(){
        SanctionContentResponseDto result = new SanctionContentResponseDto();
        return  ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public  static ResponseEntity<SanctionContentResponseDto> alreadySanctionContent(){
        SanctionContentResponseDto result = new SanctionContentResponseDto(ResponseCode.ALREADY_SANCTIONED_CONTENT, ResponseCode.ALREADY_SANCTIONED_CONTENT);
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(result);
    }
}
