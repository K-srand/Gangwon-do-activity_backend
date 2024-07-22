package com.multicampus.gangwonActivity.dto.response.user;

import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import com.multicampus.gangwonActivity.entity.User;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetSignInUserResponseDto extends ResponseDto {
    private String id;
    private String nickname;
    private String userRole;

    private GetSignInUserResponseDto(User user){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.id = user.getUserId();
        this.nickname = user.getUserNick();
        this.userRole = user.getUserRole();
//        this.profileImage = userEntity.getUserProfileImage();
    }

    public static ResponseEntity<GetSignInUserResponseDto> success(User user){
        GetSignInUserResponseDto result = new GetSignInUserResponseDto(user);
        return  ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXITSTED_USER, ResponseMessage.NOT_EXITSTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);

    }


}
