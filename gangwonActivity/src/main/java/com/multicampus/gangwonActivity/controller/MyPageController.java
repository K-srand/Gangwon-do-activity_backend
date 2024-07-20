package com.multicampus.gangwonActivity.controller;


import com.multicampus.gangwonActivity.dto.request.mypage.CheckPasswordRequestDto;
import com.multicampus.gangwonActivity.dto.request.board.MyCourseUploadRequestDto;
import com.multicampus.gangwonActivity.dto.request.mypage.ModifyMyInfoRequestDto;
import com.multicampus.gangwonActivity.dto.response.board.GetBoardListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.mypage.*;
import com.multicampus.gangwonActivity.entity.Board;
import com.multicampus.gangwonActivity.service.MyPageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/getmyboardlist")
    public ResponseEntity<PageImpl<GetBoardListResponseDto>> getMyPage(
            @AuthenticationPrincipal String id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        SearchPageDto searchPageDto = new SearchPageDto();
        searchPageDto.setPage(page);
        searchPageDto.setSize(size);

        List<GetBoardListResponseDto> myboardListByPage = myPageService.getMyPageBoard(id, searchPageDto);
        int myBoardCount = myPageService.countBoardByUserId(id);

        return ResponseEntity.ok(new PageImpl<>(myboardListByPage, PageRequest.of(page, size), myBoardCount));
    }

    @GetMapping("/getmyfavoritelist")
    public ResponseEntity<PageImpl<GetMyFavoritesListResponseDto>> getMyFavorites(
            @AuthenticationPrincipal String id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        SearchPageDto searchPageDto = new SearchPageDto();
        searchPageDto.setPage(page);
        searchPageDto.setSize(size);

        List<GetMyFavoritesListResponseDto> favoritesList = myPageService.getMyFavorites(id, searchPageDto);

        int favoritesCount = myPageService.countAllMyFavorites(id);
        return ResponseEntity.ok(new PageImpl<>(favoritesList, PageRequest.of(page, size), favoritesCount));
    }


    @DeleteMapping("/delete/{placeNo}")
    public ResponseEntity<? super MyPageResponseDto> deleteMyFavorite(
            @PathVariable("placeNo") Long placeNo,
            @AuthenticationPrincipal String id) {
        ResponseEntity<? super MyPageResponseDto> getMyFavoritesListResponseDtoResponseEntity = myPageService.deleteMyFavorites(placeNo);
        return getMyFavoritesListResponseDtoResponseEntity;
    }

    @PostMapping("/modify")
    public ResponseEntity<? super ModifyMyInfoResponseDto> modifyMyInfo(
            @AuthenticationPrincipal String id,
            @RequestBody @Valid ModifyMyInfoRequestDto requestDto
    ) {
        ResponseEntity<? super ModifyMyInfoResponseDto> response = myPageService.modifyMyInfo(id, requestDto);
        return response;
    }


    @PostMapping ("/deleteuser")
    public ResponseEntity<? super ModifyMyInfoResponseDto> deleteuser(
            @AuthenticationPrincipal String id,
            @RequestBody @Valid ModifyMyInfoRequestDto requestDto
    ) {
        ResponseEntity<? super ModifyMyInfoResponseDto> response = myPageService.deleteMyInfo(id, requestDto);
        return  response;
    }

    @GetMapping("/userinfo")
    public ResponseEntity<ModMyInfoResponseDto> getUserInfo(
            @AuthenticationPrincipal String id
    ) {
        ModMyInfoResponseDto response = myPageService.modMyInfo(id);
        return ResponseEntity.ok(response);
    }
    //규진님 패스워드 체킹
    @PostMapping("/checkpassword")
    public ResponseEntity<? super MyPageResponseDto> checkPassword(
            @AuthenticationPrincipal String id, @RequestBody @Valid CheckPasswordRequestDto requestDto
    ){
        ResponseEntity<? super ModifyMyInfoResponseDto> response = myPageService.checkPassword(id, requestDto);
        return (ResponseEntity<? super MyPageResponseDto>) response;
    }
    //내 추천코스 (민호형 & 수지)

    @GetMapping("/mycourse")
    public ResponseEntity<PageImpl<GetMyPageCourseResponseDto>> getMyCourse(
            @AuthenticationPrincipal String id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "1") int size) {
        SearchPageDto searchPageDto = new SearchPageDto();
        searchPageDto.setPage(page);
        searchPageDto.setSize(size);
        List<GetMyPageCourseResponseDto> myCourse = myPageService.getMyCourse(id);
        int myCourseCount = myPageService.countMyCourse(id);

        return ResponseEntity.ok(
                new PageImpl<>(myCourse, PageRequest.of(page, size), myCourseCount));
    }

    //작성글 코스 지우기
    @DeleteMapping("/deletemycourse/{myCourseNo}")
    public ResponseEntity<? super MyPageResponseDto> deleteMyCourse(
            @PathVariable("myCourseNo") Long myCourseNo,
            @AuthenticationPrincipal String id) {
        ResponseEntity<? super MyPageResponseDto> deleteMyCourse = myPageService.deleteMyCourse(myCourseNo);
        return deleteMyCourse;
    }

    @GetMapping("/exp")
    public ResponseEntity<? super GetMyExpResponseDto> getMyExp(
            @AuthenticationPrincipal String id){
        int rank = myPageService.getUserExp(id);
        return ResponseEntity.ok(rank);
    }

}

