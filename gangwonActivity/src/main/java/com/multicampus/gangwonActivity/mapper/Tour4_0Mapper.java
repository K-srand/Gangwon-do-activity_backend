package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.entity.Tour4_0;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface Tour4_0Mapper {
    List<Tour4_0> findEachTwoByRating();

    List<Tour4_0> selectPlaceTitlesWithDistance(@Param("mapx") double mapx, @Param("mapy") double mapy);

    @Options(useGeneratedKeys = true, keyProperty = "placeNo", keyColumn = "place_no")
    void saveAll(List<Tour4_0> list);

    List<Tour4_0> selectPlaceRestaurantWithDistance(@Param("mapx") double mapx, @Param("mapy") double mapy);

    List<Tour4_0> selectPlaceCafeWithDistance(@Param("mapx") double mapx, @Param("mapy") double mapy);

    List<Tour4_0> selectPlaceTourWithDistance(@Param("mapx") double mapx, @Param("mapy") double mapy);

    List<Tour4_0> selectPlaceAccommodationWithDistance(@Param("mapx") double mapx, @Param("mapy") double mapy);

    List<Tour4_0> selectPlaceActivityWithDistance(@Param("mapx") double mapx, @Param("mapy") double mapy);

}
