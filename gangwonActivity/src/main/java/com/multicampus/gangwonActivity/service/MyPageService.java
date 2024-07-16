package com.multicampus.gangwonActivity.service;


import com.multicampus.gangwonActivity.dto.response.board.GetBoardListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.mypage.GetMyFavoritesListResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.MyPageResponseDto;
import com.multicampus.gangwonActivity.entity.Board;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MyPageService {
    List<GetBoardListResponseDto> getMyPageBoard(String id , SearchPageDto searchPageDto);

    List<GetMyFavoritesListResponseDto> getMyFavorites(String id, SearchPageDto searchPageDto);

    Integer countAllMyFavorites(String id);

    Integer countBoardByUserId(String id);

    ResponseEntity<? super MyPageResponseDto> deleteMyFavorites(Long placeNo);
}
