package com.multicampus.gangwonActivity.service;

import com.multicampus.gangwonActivity.dto.request.auth.SignInRequestDto;
import com.multicampus.gangwonActivity.dto.request.mypage.ModifyMyInfoRequestDto;
import com.multicampus.gangwonActivity.dto.response.admin.AdminUserListResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignInResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.mypage.ModifyMyInfoResponseDto;
import com.multicampus.gangwonActivity.dto.response.sanction.SanctionContentResponseDto;
import com.multicampus.gangwonActivity.dto.response.sanction.SanctionedUserResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
//    ResponseEntity<? super SignInResponseDto> AdminIn(SignInRequestDto dto);
    List<AdminUserListResponseDto> userList(SearchPageDto searchPageDto);

    int countUser();


    ResponseEntity<? super SanctionedUserResponseDto> sanctionUser(String id, Long userNo);
    ResponseEntity<? super SanctionedUserResponseDto> disSanctionUser(String id, Long userNo);

    ResponseEntity<? super SanctionContentResponseDto> sanctionContent(Long reportedContentNo);

    ResponseEntity<? super SanctionContentResponseDto> disSanctionContent(Long reportedContentNo);

    ResponseEntity<? super ModifyMyInfoResponseDto> reuseInfo(ModifyMyInfoRequestDto dto);
}
