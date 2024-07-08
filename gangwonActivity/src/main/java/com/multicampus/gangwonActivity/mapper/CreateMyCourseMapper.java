package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.entity.MyFavoritesEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreateMyCourseMapper {
    List<MyFavoritesEntity> selectMyFavoritesActivity();

    List<MyFavoritesEntity> selectMyFavoritesRestaurant();

    List<MyFavoritesEntity> selectMyFavoritesCafe();

    List<MyFavoritesEntity> selectMyFavoritesTour();

    List<MyFavoritesEntity> selectMyFavoritesAccommodation();
}
