package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.entity.ReportedContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReportMapper {

    //  신고자의 userNo, 작성글의 boardNo를 통해서 글의 존재 여부를 판단.
    Boolean alreadyReportedBoard(@Param("boardNo") Long boardNo, @Param("userNo")Long userNo);

    // 모든 역경을 뚫고 작성글 신고를 생성해보자!!!
    void saveReportedBoard(ReportedContent reportedContent);
}
