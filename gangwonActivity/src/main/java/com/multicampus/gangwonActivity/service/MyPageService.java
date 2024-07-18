package com.multicampus.gangwonActivity.service;


import com.multicampus.gangwonActivity.dto.request.mypage.CheckPasswordRequestDto;
import com.multicampus.gangwonActivity.dto.request.mypage.ModifyMyInfoRequestDto;
import com.multicampus.gangwonActivity.dto.response.board.GetBoardListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.mypage.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface MyPageService {
    List<GetBoardListResponseDto> getMyPageBoard(String id , SearchPageDto searchPageDto);

    List<GetMyFavoritesListResponseDto> getMyFavorites(String id, SearchPageDto searchPageDto);

    Integer countAllMyFavorites(String id);

    Integer countBoardByUserId(String id);

    ResponseEntity<? super MyPageResponseDto> deleteMyFavorites(Long placeNo);

    // 규진님 파트
    ResponseEntity<? super ModifyMyInfoResponseDto> modifyMyInfo(String id, ModifyMyInfoRequestDto dto);

    ResponseEntity<? super ModifyMyInfoResponseDto> deleteMyInfo(String id, ModifyMyInfoRequestDto dto);

    public ModMyInfoResponseDto modMyInfo(String id);

    ResponseEntity<? super ModifyMyInfoResponseDto> checkPassword(String id, CheckPasswordRequestDto dto);
    //내추천코스 (수지&민호형)

    List<GetMyPageCourseResponseDto> getMyCourse(String userId);

    int countMyCourse(String userId);

   void deleteMyCourse(Long myCourseNo);

    int getUserExp(String id);
}
