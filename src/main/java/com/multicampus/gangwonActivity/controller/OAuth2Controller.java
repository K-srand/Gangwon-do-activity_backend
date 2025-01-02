package com.multicampus.gangwonActivity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {

    @GetMapping("/oauth2/callback/kakao")
    public String kakaoCallback(String code) {
        return "카카오 인증 완료: " + code;
    }
}
