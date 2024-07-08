package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.entity.Tour4_0Entity;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface Tour4_0Mapper {
    List<Tour4_0Entity> findEachTwoByRating();

    List<Tour4_0Entity> selectPlaceTitlesWithDistance(@Param("mapx") double mapx, @Param("mapy") double mapy);

    @Options(useGeneratedKeys = true, keyProperty = "placeNo", keyColumn = "place_no")
    void saveAll(List<Tour4_0Entity> list);

    List<Tour4_0Entity> selectPlaceRestaurantWithDistance(@Param("mapx") double mapx, @Param("mapy") double mapy);

    List<Tour4_0Entity> selectPlaceCafeWithDistance(@Param("mapx") double mapx, @Param("mapy") double mapy);

    List<Tour4_0Entity> selectPlaceTourWithDistance(@Param("mapx") double mapx, @Param("mapy") double mapy);

    List<Tour4_0Entity> selectPlaceAccommodationWithDistance(@Param("mapx") double mapx, @Param("mapy") double mapy);

    List<Tour4_0Entity> selectPlaceActivityWithDistance(@Param("mapx") double mapx, @Param("mapy") double mapy);

}
