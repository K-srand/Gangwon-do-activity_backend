package com.multicampus.gangwonActivity.controller;


import com.multicampus.gangwonActivity.dto.request.mypage.ModifyMyInfoRequestDto;
import com.multicampus.gangwonActivity.dto.response.board.GetBoardListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.mypage.GetMyFavoritesListResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.ModMyInfoResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.ModifyMyInfoResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.MyPageResponseDto;
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


    @PostMapping("/deleteuser")
    public ResponseEntity<? super ModifyMyInfoResponseDto> deleteuser(
            @AuthenticationPrincipal String id
    ) {
        ResponseEntity<? super ModifyMyInfoResponseDto> response = myPageService.deleteMyInfo(id);
        return  response;
    }

    @GetMapping("/userinfo")
    public ResponseEntity<ModMyInfoResponseDto> getUserInfo(
            @AuthenticationPrincipal String id
    ) {
        ModMyInfoResponseDto response = myPageService.modMyInfo(id);
        return ResponseEntity.ok(response);
    }



}

