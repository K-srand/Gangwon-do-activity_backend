package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.entity.MyFavoritesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MyFavoriteMapper {
    Long selectUserNo(@Param("userId") String userId);

    void saveFavorite(MyFavoritesEntity myFavoritesEntity);

    boolean selectExistsFavorite(@Param("userNo") Long userNo, @Param("placeNo") Long placeNo);
}
