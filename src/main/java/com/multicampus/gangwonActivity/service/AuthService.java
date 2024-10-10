package com.multicampus.gangwonActivity.service;

import com.multicampus.gangwonActivity.dto.request.auth.CheckCertificationRequestDto;
import com.multicampus.gangwonActivity.dto.request.auth.EmailCertificationRequestDto;
import com.multicampus.gangwonActivity.dto.request.auth.SignInRequestDto;
import com.multicampus.gangwonActivity.dto.request.auth.SignUpRequestDto;
import com.multicampus.gangwonActivity.dto.response.auth.CheckCertificationResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.EmailCertificationResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignInResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignUpResponseDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto, HttpSession session);
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto, HttpSession session);

    ResponseEntity<? super CheckCertificationResponseDto> findIdCertification(CheckCertificationRequestDto dto, HttpSession session);
    ResponseEntity<? super CheckCertificationResponseDto> findPwdCertification(CheckCertificationRequestDto dto, HttpSession session);
    Boolean checkId(String userId);
    Boolean checkNickname(String userNick);

}
