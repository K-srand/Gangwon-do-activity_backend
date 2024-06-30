package com.multicampus.ganwonActivity.dto.response.api;

import com.multicampus.ganwonActivity.common.ResponseCode;
import com.multicampus.ganwonActivity.common.ResponseMessage;
import com.multicampus.ganwonActivity.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PlaceApiResponseDto extends ResponseDto {

    //성공시
    public PlaceApiResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }


    public static ResponseEntity<PlaceApiResponseDto> success(){
        PlaceApiResponseDto result = new PlaceApiResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //실패시
    public static ResponseEntity<PlaceApiResponseDto> fail(){
        PlaceApiResponseDto result = new PlaceApiResponseDto();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
