package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.entity.MyCourseEntity;
import com.multicampus.gangwonActivity.entity.MyCourseMappingEntity;
import com.multicampus.gangwonActivity.entity.MyFavoritesEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CreateMyCourseMapper {
    List<MyFavoritesEntity> selectMyFavoritesActivity(@Param("userNo") Long userNo);

    List<MyFavoritesEntity> selectMyFavoritesRestaurant(@Param("userNo") Long userNo);

    List<MyFavoritesEntity> selectMyFavoritesCafe(@Param("userNo") Long userNo);

    List<MyFavoritesEntity> selectMyFavoritesTour(@Param("userNo") Long userNo);

    List<MyFavoritesEntity> selectMyFavoritesAccommodation(@Param("userNo") Long userNo);

    Long selectUserNo(@Param("userId") String userId);

    @Options(useGeneratedKeys = true, keyProperty = "myCourseNo", keyColumn = "myCourseNo")
    void saveMyCourse(MyCourseEntity myCourseEntity);

    void saveMyCourseMapping(MyCourseMappingEntity myCourseMappingEntity);

    boolean selectExistsMyCourse(
            @Param("userNo") Long userNo,
            @Param("placeNo1") Long placeNo1, @Param("orderNo1") int orderNo1,
            @Param("placeNo2") Long placeNo2, @Param("orderNo2") int orderNo2,
            @Param("placeNo3") Long placeNo3, @Param("orderNo3") int orderNo3,
            @Param("placeNo4") Long placeNo4, @Param("orderNo4") int orderNo4
    );
}
