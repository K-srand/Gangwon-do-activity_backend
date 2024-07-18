package com.multicampus.gangwonActivity.mapper;

import com.multicampus.gangwonActivity.dto.response.report.ReportListResponseDto;
import com.multicampus.gangwonActivity.entity.Board;
import com.multicampus.gangwonActivity.entity.ReportedContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.time.LocalDateTime;

@Mapper
public interface ReportMapper {

    //  신고자의 userNo, 작성글의 boardNo를 통해서 글의 존재 여부를 판단.
    Boolean alreadyReportedBoard(@Param("boardNo") Long boardNo, @Param("userNo")Long userNo);

    // 모든 역경을 뚫고 작성글 신고를 생성해보자!!!
    void saveReportedBoard(ReportedContent reportedContent);

    // 댓글 신고를 했는가
    Boolean alreadyReportedComment(@Param("commentNo") Long commentNo, @Param("userNo")Long userNo);

    //댓글 신고
    void saveReportedComment(ReportedContent reportedContent);


    // 제재 쪽인데 꼽사리좀 낄게요.... mapper 새로 만들기 귀찮아서 문의 :정석
    Boolean alreadySanctionedUser(@Param("userNo")Long userNo);

    void sanctionUser(@Param("userNo")Long userNo, @Param("localDateTime") LocalDateTime localDateTime);

    void desanctionUser(@Param("userNo")Long userNo);

    //신고 목록
    List<ReportListResponseDto> findAllReportedContent();

    //신고받은 글/댓글 삭제
    void deleteReport(@Param("reportedContentNo") Long reportedContentNo);
}
