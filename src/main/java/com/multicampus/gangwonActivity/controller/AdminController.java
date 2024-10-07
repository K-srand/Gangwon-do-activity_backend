package com.multicampus.gangwonActivity.controller;


import com.multicampus.gangwonActivity.dto.request.auth.SignInRequestDto;
import com.multicampus.gangwonActivity.dto.request.board.PostBoardRequestDto;
import com.multicampus.gangwonActivity.dto.request.mypage.ModifyMyInfoRequestDto;
import com.multicampus.gangwonActivity.dto.response.admin.AdminUserListResponseDto;
import com.multicampus.gangwonActivity.dto.response.auth.SignInResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.PostBoardResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.mypage.ModifyMyInfoResponseDto;
import com.multicampus.gangwonActivity.dto.response.sanction.SanctionContentResponseDto;
import com.multicampus.gangwonActivity.dto.response.sanction.SanctionedUserResponseDto;
import com.multicampus.gangwonActivity.dto.response.sanction.SanctionedUserResponseDto;
import com.multicampus.gangwonActivity.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

//    @PostMapping("/sign-in")
//    public ResponseEntity<? super SignInResponseDto> adminIn(@RequestBody @Valid SignInRequestDto requestBody){
//        ResponseEntity<? super SignInResponseDto> response = adminService.AdminIn(requestBody);
//        return response;
//    }

    @GetMapping("/getuserlist")
    public ResponseEntity<PageImpl<AdminUserListResponseDto>> userList(
            @RequestParam(value = "page", defaultValue = "0")int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ){
        SearchPageDto searchPageDto = new SearchPageDto();
        searchPageDto.setPage(page);
        searchPageDto.setSize(size);
        List<AdminUserListResponseDto> userEntityList = adminService.userList(searchPageDto);
        int userCount = adminService.countUser();

        return ResponseEntity.ok(
                new PageImpl<>(userEntityList, PageRequest.of(searchPageDto.getPage(), searchPageDto.getSize()), userCount));

    }

    //제재하기 관리자 팀을 위해서 기증하겠습니다.
    @PatchMapping("/sanction")
    public  ResponseEntity<? super SanctionedUserResponseDto> restrictUser(
            @AuthenticationPrincipal String id,
            @RequestBody Long userNo
    ){
        ResponseEntity<? super SanctionedUserResponseDto> response = adminService.sanctionUser(id, userNo);

        return response;
    }

    //제재 해지
    @PatchMapping("/desanction")
    public ResponseEntity<? super  SanctionedUserResponseDto> disRestrictUser(
            @AuthenticationPrincipal String id,
            @RequestBody Long userNo
    ){
       ResponseEntity<? super  SanctionedUserResponseDto> response = adminService.disSanctionUser(id, userNo);

       return response;

    }

    // 콘텐츠 제재
    @PatchMapping("/sanctioncontent")
    public ResponseEntity<? super SanctionContentResponseDto> restrictContent(
            @RequestBody Long reportedContentNo
    ){
        ResponseEntity<? super SanctionContentResponseDto> response = adminService.sanctionContent(reportedContentNo);

        return response;
    }

    // 콘텐츠 제재 해제
    @PatchMapping("/desanctioncontent")
    public ResponseEntity<? super SanctionContentResponseDto> disRestrictContent(
            @RequestBody Long reportedContendNo
    ){
        ResponseEntity<? super SanctionContentResponseDto> response = adminService.disSanctionContent(reportedContendNo);

        return response;
    }

    //탈퇴한 유저 해제
    @PatchMapping("/reuser")
    public ResponseEntity<? super ModifyMyInfoResponseDto> reuser(
            @RequestBody @Valid ModifyMyInfoRequestDto requestDto
    ) {
        ResponseEntity<? super ModifyMyInfoResponseDto> response = adminService.reuseInfo(requestDto);
        return  response;
    }


}
