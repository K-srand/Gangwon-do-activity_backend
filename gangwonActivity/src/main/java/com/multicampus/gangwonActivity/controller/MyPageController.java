package com.multicampus.gangwonActivity.controller;


import com.multicampus.gangwonActivity.dto.response.mypage.MyPageResponseDto;
import com.multicampus.gangwonActivity.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("")
    public ResponseEntity<? super MyPageResponseDto> getMyPage(
            @AuthenticationPrincipal String id
    ){

        ResponseEntity<? super MyPageResponseDto> myPageResponseDto = myPageService.getMyPage(id);
        return myPageResponseDto;
    }




}
