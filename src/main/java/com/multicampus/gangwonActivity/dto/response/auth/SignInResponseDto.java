package com.multicampus.gangwonActivity.dto.response.auth;


import com.multicampus.gangwonActivity.common.ResponseCode;
import com.multicampus.gangwonActivity.common.ResponseMessage;
import com.multicampus.gangwonActivity.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDto extends ResponseDto {

    private String role;
    private  String token;
    private int expirationTime;

    private SignInResponseDto(String token, String role){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.role = role;
        this.token = token;
        this.expirationTime = 3600; //1시간
    }


    //로그인 성공
    public static ResponseEntity<SignInResponseDto> success(String token, String role){
        SignInResponseDto result = new SignInResponseDto(token, role);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> signInFailed(){
        ResponseDto result = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }


    public static ResponseEntity<ResponseDto> adminSignInFailed(){
        ResponseDto result = new ResponseDto(ResponseCode.AUTHORIZATION_FAIL, ResponseMessage.AUTHORIZATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }


}
