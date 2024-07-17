package com.multicampus.gangwonActivity.dto.response.mypage;

import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter

public class ModifyMyInfoResponseDto extends ResponseDto{


    public ModifyMyInfoResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<ModifyMyInfoResponseDto> success(){
        ModifyMyInfoResponseDto result = new ModifyMyInfoResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> passwordInFailed(){
        ResponseDto result = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    public static ResponseEntity<ResponseDto> nickName() {
        ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    public static  ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXITSTED_USER, ResponseMessage.NOT_EXITSTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
