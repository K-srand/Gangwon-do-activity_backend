package com.multicampus.gangwonActivity.controller;


import com.multicampus.gangwonActivity.dto.response.user.GetSignInUserResponseDto;
import com.multicampus.gangwonActivity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    //유저 정보 서비스 호출
    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
            //userId 꺼내오기 jwtAuthenticationFilter에서
            @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(userId);
        return response;
    }


}
