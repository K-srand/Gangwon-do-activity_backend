package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.dto.response.admin.AdminUserListResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.mypage.ModMyInfoResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<AdminUserListResponseDto> allUserList(SearchPageDto searchPageDto);

    Integer countAllUser();

    ModMyInfoResponseDto userInfo(@Param("userNo")Long userNo);

}
