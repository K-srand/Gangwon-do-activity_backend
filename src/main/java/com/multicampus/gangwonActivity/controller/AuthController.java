package com.multicampus.gangwonActivity.controller;

import com.multicampus.gangwonActivity.dto.request.auth.SignInRequestDto;
import com.multicampus.gangwonActivity.dto.request.auth.SignUpRequestDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignInResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignUpResponseDto;
import com.multicampus.gangwonActivity.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import com.multicampus.gangwonActivity.dto.request.auth.CheckCertificationRequestDto;
import com.multicampus.gangwonActivity.dto.request.auth.EmailCertificationRequestDto;
import com.multicampus.gangwonActivity.dto.response.auth.CheckCertificationResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.EmailCertificationResponseDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
//    private HttpSession httpSession;

    //회원가입 서비스 호출
    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto requestBody
    ){
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    //로그인 서비스 호출
    @PostMapping("/sign-in")
    public  ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto requestBody
    ){
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    //아이디 중복 서비스 호출
    @PostMapping("/check-id")
    public ResponseEntity<Boolean> checkId(@NotNull @RequestBody Map<String, String> body) {
        String userId = body.get("userId");
        boolean isValid = authService.checkId(userId);
        return ResponseEntity.ok(isValid);
    }

    //닉네임 중복 서비스 호출
    @PostMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNickname(@NotNull @RequestBody Map<String, String> body) {
        String userNick = body.get("userNick");
        boolean isValid = authService.checkNickname(userNick);
        return ResponseEntity.ok(isValid);
    }

    // 이메일 인증 서비스 호출
    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
            @RequestBody @Validated EmailCertificationRequestDto requestBody,
            HttpSession session) {
        return authService.emailCertification(requestBody, session);
    }

    // 인증번호 확인 서비스 호출
    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
            @RequestBody @Validated CheckCertificationRequestDto requestBody,
            HttpSession session) {
        return authService.checkCertification(requestBody, session);
    }


    //아이디 찾기 서비스 호출
    @PostMapping("/findId-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> findIdCertification(
            @RequestBody @Validated CheckCertificationRequestDto requestBody,
            HttpSession session) {
        System.out.println("Session mail: "+ (String) session.getAttribute("email") );
        System.out.println("Session certification: "+ (String) session.getAttribute("certificationNumber") );
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.findIdCertification(requestBody, session);
        return response;
    }

    //새 비밀번호 발급 서비스 호출
    @PostMapping("/findPwd-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> findPwdCertification(
            @RequestBody @Validated CheckCertificationRequestDto requestBody,
            HttpSession session) {
        System.out.println("Session mail: "+ (String) session.getAttribute("email") );
        System.out.println("Session certificationNumber: "+ (String) session.getAttribute("certificationNumber") );
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.findPwdCertification(requestBody, session);
        return response;
    }


}
