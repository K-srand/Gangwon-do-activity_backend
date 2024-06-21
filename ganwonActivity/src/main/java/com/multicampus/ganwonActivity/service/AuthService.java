package com.multicampus.ganwonActivity.service;

import com.multicampus.ganwonActivity.dto.request.auth.SignInRequestDto;
import com.multicampus.ganwonActivity.dto.request.auth.SignUpRequestDto;
import com.multicampus.ganwonActivity.dto.response.auth.SignInResponseDto;
import com.multicampus.ganwonActivity.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

}
