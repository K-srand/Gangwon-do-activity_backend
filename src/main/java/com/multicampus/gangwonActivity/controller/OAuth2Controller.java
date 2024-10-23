package com.multicampus.gangwonActivity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class OAuth2Controller {

    @GetMapping("/oauth2/callback/kakao")
    public @ResponseBody String kakaoCallback() {
        return "카카오 인증 완료";
    }
}
