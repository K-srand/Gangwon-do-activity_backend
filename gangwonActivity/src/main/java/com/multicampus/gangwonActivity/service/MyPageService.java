package com.multicampus.gangwonActivity.service;


import com.multicampus.gangwonActivity.dto.response.mypage.MyPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface MyPageService {
    ResponseEntity<? super MyPageResponseDto> getMyPage(String id);


}
