package com.multicampus.ganwonActivity.mapper;

import com.multicampus.ganwonActivity.dto.response.board.GetBoardListResponseDto;
import com.multicampus.ganwonActivity.entity.BoardEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {
//    @Select("SELECT b.boardNo, b.userNo, b.boardTitle, b.content, b.countLikes, b.hasImage, b.writtenTime, b.isReported, b.myCourseNo, u.userNick " +
//            "FROM board b LEFT JOIN user u ON b.userNo = u.userNo")
    List<GetBoardListResponseDto> findAllWithUser();
}
