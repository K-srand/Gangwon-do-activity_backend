package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.dto.response.board.GetBoardCommentListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.GetBoardDetailResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.GetBoardListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.entity.Board;
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

    //낌&빡
    Long selectUserNo(@Param("userId") String userId);

    List<Map<String, Object>> findMyCourse(@Param("userNo") Long userNo);

    Integer countMyCourse(@Param("userNo") Long userNo);

    List<Map<String, Object>> selectMyCourse(@Param("myCourseNo") Long myCourseNo);

    void deleteMyCourse(@Param("boardNo") Long boardNo);

    void updateMyCourse(@Param("boardNo") Long boardNo, @Param("myCourseNo") Long myCourseNo);

    List<Board> getBoardListByUserNo(Long userNo);

    //    Integer countBoardByUserNo(Long userNo);
    List<GetBoardListResponseDto> getBoardListByUserNoPaged(@Param("userNo") Long userNo,@Param("searchPageDto")  SearchPageDto searchPageDto);


    //예원 파트
    void like(@Param("boardNo") Long boardNo, @Param("userNo") Long userNo);

    void dislike(@Param("boardNo") Long boardNo, @Param("userNo")Long userNo);

    String likeChecked(@Param("boardNo") Long boardNo, @Param("userNo")Long userNo);

    void unlike(@Param("boardNo") long boardNo, @Param("userNo") long userNo);

    void undislike(@Param("boardNo") long boardNo, @Param("userNo") long userNo);

    void incrementExpBoardWrite(@Param("userNo") Long userNo);

    void incrementExpCommentWrite(@Param("userNo") Long userNo);
}
