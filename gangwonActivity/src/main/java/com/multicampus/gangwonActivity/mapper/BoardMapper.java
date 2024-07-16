package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.dto.response.board.GetBoardCommentListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.GetBoardDetailResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.GetBoardListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    List<GetBoardListResponseDto> findAllWithUser(SearchPageDto searchPageDto);

    Boolean alreadyLiked(@Param("boardNo") Long boardNo, @Param("userNo")Long userNo);

    List<GetBoardCommentListResponseDto> findCommentsByBoardNo(@Param("boardNo")Long boardNo, @Param("searchPageDto")SearchPageDto searchPageDto);

    GetBoardDetailResponseDto findBoardDetailWithUser(@Param("boardNo")Long boardNo);

    Integer countAllWithBoard();

    Integer countAllWithComment(@Param("boardNo")Long boardNo);

    List<String> findAllImage(@Param("boardNo") Long boardNo);

    void incrementViewsByBoardNo(@Param("boardNo") Long boardNo);

    List<GetBoardListResponseDto> getBestPosts();

    void deleteImageFile(@Param("imageAddress")String imageAddress);

    Long selectUserNo(@Param("userId") String userId);

    List<Map<String, Object>> findMyCourse(@Param("userNo") Long userNo);

    Integer countMyCourse(@Param("userNo") Long userNo);

    List<Map<String, Object>> selectMyCourse(@Param("myCourseNo") Long myCourseNo);

    void deleteMyCourse(@Param("boardNo") Long boardNo);

    void updateMyCourse(@Param("boardNo") Long boardNo, @Param("myCourseNo") Long myCourseNo);
}
