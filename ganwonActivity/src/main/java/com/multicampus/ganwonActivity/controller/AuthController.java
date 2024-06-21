package com.multicampus.ganwonActivity.controller;

import com.multicampus.ganwonActivity.dto.request.auth.SignInRequestDto;
import com.multicampus.ganwonActivity.dto.request.auth.SignUpRequestDto;
import com.multicampus.ganwonActivity.dto.response.auth.SignInResponseDto;
import com.multicampus.ganwonActivity.dto.response.auth.SignUpResponseDto;
import com.multicampus.ganwonActivity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            @RequestBody @Validated SignUpRequestDto requestBody
    ){
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public  ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Validated SignInRequestDto requestBody
    ){
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

}
