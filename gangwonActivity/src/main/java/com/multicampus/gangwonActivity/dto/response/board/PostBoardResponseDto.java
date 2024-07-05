package com.multicampus.gangwonActivity.dto.response.board;

import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PostBoardResponseDto extends ResponseDto {


    private  PostBoardResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS)    ;
    }

    public static ResponseEntity<PostBoardResponseDto> success(){
        PostBoardResponseDto result = new PostBoardResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static  ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXITSTED_USER, ResponseMessage.NOT_EXITSTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
