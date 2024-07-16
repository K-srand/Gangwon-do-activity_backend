package com.multicampus.gangwonActivity.service;


import com.multicampus.gangwonActivity.dto.request.mypage.ModifyMyInfoRequestDto;
import com.multicampus.gangwonActivity.dto.response.board.GetBoardListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.mypage.GetMyFavoritesListResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.ModMyInfoResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.ModifyMyInfoResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.MyPageResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface MyPageService {
    List<GetBoardListResponseDto> getMyPageBoard(String id , SearchPageDto searchPageDto);

    List<GetMyFavoritesListResponseDto> getMyFavorites(String id, SearchPageDto searchPageDto);

    Integer countAllMyFavorites(String id);

    Integer countBoardByUserId(String id);

    ResponseEntity<? super MyPageResponseDto> deleteMyFavorites(Long placeNo);

    ResponseEntity<? super ModifyMyInfoResponseDto> modifyMyInfo(String id, ModifyMyInfoRequestDto dto);

    ResponseEntity<? super ModifyMyInfoResponseDto> deleteMyInfo(String id);

    public ModMyInfoResponseDto modMyInfo(String id);


}
