package com.multicampus.gangwonActivity.dto.response.user;

import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import com.multicampus.gangwonActivity.entity.UserEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetSignInUserResponseDto extends ResponseDto {
    private String id;
    private String nickname;
//    private String profileImage;

    private GetSignInUserResponseDto(UserEntity userEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.id = userEntity.getUserId();
        this.nickname = userEntity.getUserNick();
//        this.profileImage = userEntity.getUserProfileImage();
    }

    public static ResponseEntity<GetSignInUserResponseDto> success(UserEntity userEntity){
        GetSignInUserResponseDto result = new GetSignInUserResponseDto(userEntity);
        return  ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXITSTED_USER, ResponseMessage.NOT_EXITSTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);

    }


}
