package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.dto.response.board.GetBoardListResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<GetBoardListResponseDto> findAllWithUser();
}
