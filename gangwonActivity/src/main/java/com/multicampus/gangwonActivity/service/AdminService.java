package com.multicampus.gangwonActivity.service;

import com.multicampus.gangwonActivity.dto.request.auth.SignInRequestDto;
import com.multicampus.gangwonActivity.dto.response.admin.AdminUserListResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignInResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    ResponseEntity<? super SignInResponseDto> AdminIn(SignInRequestDto dto);
    List<AdminUserListResponseDto> userList(SearchPageDto searchPageDto);

    int countUser();


}
