package com.multicampus.gangwonActivity.controller;

import com.multicampus.gangwonActivity.dto.request.auth.SignInRequestDto;
import com.multicampus.gangwonActivity.dto.request.auth.SignUpRequestDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignInResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignUpResponseDto;
import com.multicampus.gangwonActivity.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import com.multicampus.gangwonActivity.common.CertificationNumber;
import com.multicampus.gangwonActivity.dto.request.auth.CheckCertificationRequestDto;
import com.multicampus.gangwonActivity.dto.request.auth.EmailCertificationRequestDto;
import com.multicampus.gangwonActivity.dto.response.auth.CheckCertificationResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.EmailCertificationResponseDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
//    private HttpSession httpSession;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto requestBody
    ){
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public  ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto requestBody
    ){
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    @PostMapping("/check-id")
    public ResponseEntity<Boolean> checkId(@NotNull @RequestBody Map<String, String> body) {
        String userId = body.get("userId");
        boolean isValid = authService.checkId(userId);
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNickname(@NotNull @RequestBody Map<String, String> body) {
        String userNick = body.get("userNick");
        boolean isValid = authService.checkNickname(userNick);
        return ResponseEntity.ok(isValid);
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
            @RequestBody @Validated EmailCertificationRequestDto requestBody,
            HttpSession session) {
        String email = requestBody.getEmail();
        String certificationNumber = CertificationNumber.getCertificationNumber(); // 난수 생성

        session.setAttribute("email", email);
        session.setAttribute("certificationNumber", certificationNumber);

        System.out.println("EmailRequest Body:" + email);
        System.out.println("Controller/email-certification : " + session.getAttribute("certificationNumber"));
        return authService.emailCertification(requestBody, session);
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
            @RequestBody @Validated CheckCertificationRequestDto requestBody,
            HttpSession session) {
        System.out.println("CheckRequest Body: " + requestBody.getEmail());
        System.out.println("CheckRequest Body: " + requestBody.getCertificationNumber());
        System.out.println("Session mail: " + session.getAttribute("email"));
        System.out.println("Session certificationNumber: " + session.getAttribute("certificationNumber"));
        return authService.checkCertification(requestBody, session);
    }


    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @PostMapping("/findId-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> findIdCertification(
            @RequestBody @Validated CheckCertificationRequestDto requestBody,
            HttpSession session) {
        System.out.println("Session mail: "+ (String) session.getAttribute("email") );
        System.out.println("Session certification: "+ (String) session.getAttribute("certificationNumber") );
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.findIdCertification(requestBody, session);
        return response;
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
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
