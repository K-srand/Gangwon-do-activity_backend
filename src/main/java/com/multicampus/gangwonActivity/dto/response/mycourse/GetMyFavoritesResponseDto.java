package com.multicampus.gangwonActivity.dto.response.mycourse;

import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetMyFavoritesResponseDto extends ResponseDto {
    public GetMyFavoritesResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<GetMyFavoritesResponseDto> success() {
        GetMyFavoritesResponseDto result = new GetMyFavoritesResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> favoriteExists() {
        ResponseDto result = new ResponseDto(ResponseCode.FAVORITE_EXISTS, ResponseMessage.FAVORITE_EXISTS);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
}
