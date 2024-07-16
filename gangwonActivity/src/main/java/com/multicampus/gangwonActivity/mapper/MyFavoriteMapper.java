package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.mypage.GetMyFavoritesListResponseDto;
import com.multicampus.gangwonActivity.entity.MyFavoritesUserPlace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyFavoriteMapper {
    Long selectUserNo(@Param("userId") String userId);

    void saveFavorite(MyFavoritesUserPlace myFavoritesUserPlace);

    boolean selectExistsFavorite(@Param("userNo") Long userNo, @Param("placeNo") Long placeNo);

    List<GetMyFavoritesListResponseDto> getMyFavoritesByUserNo(Long userNo);

    //마이페이지 부분을 위해 넣어줌 -> 문의 : 정석
    List<GetMyFavoritesListResponseDto> findMyFavoritesByUserNo(@Param("userNo") Long userNo, @Param("searchPageDto")SearchPageDto searchPageDto);

    void deleteMyFavoritesByPlaceNo(Long placeNo);

}