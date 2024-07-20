package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.mypage.GetMyPageCourseResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.GetMyExpResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.GetMyFavoritesListResponseDto;
import com.multicampus.gangwonActivity.dto.response.mypage.MyPageResponseDto;
import com.multicampus.gangwonActivity.entity.MyFavoritesUserPlace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyFavoriteMapper {
    Long selectUserNo(@Param("userId") String userId);

    void saveFavorite(MyFavoritesUserPlace myFavoritesUserPlace);

    boolean selectExistsFavorite(@Param("userNo") Long userNo, @Param("placeNo") Long placeNo);

    List<GetMyFavoritesListResponseDto> getMyFavoritesByUserNo(Long userNo);

    //마이페이지 부분을 위해 넣어줌 -> 문의 : 정석
    List<GetMyFavoritesListResponseDto> findMyFavoritesByUserNo(@Param("userNo") Long userNo, @Param("searchPageDto")SearchPageDto searchPageDto);

    void deleteMyFavoritesByPlaceNo(Long placeNo);

    //내추천코스 (민호형, 수지)

    List<GetMyPageCourseResponseDto> findMyCourse(@Param("userNo") Long userNo);

    Integer countMyCourse(@Param("userNo") Long userNo);

    void deleteMyCourse(@Param("myCourseNo") Long myCourseNo);

    Integer getMyExp(@Param("userNo") Long userNo);

}