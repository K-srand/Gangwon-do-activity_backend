package com.multicampus.gangwonActivity.service;


import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.report.ReportListResponseDto;
import com.multicampus.gangwonActivity.dto.response.report.ReportedContentResponseDto;
import org.springframework.http.ResponseEntity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ReportService {

    ResponseEntity<? super ReportedContentResponseDto> reportBoard( Long boardNo, String id);
    ResponseEntity<? super ReportedContentResponseDto> reportComment(Long commentNo, String id);

    List<ReportListResponseDto> listReport(SearchPageDto searchPageDto);

    void reportDelete(Long reportedContentNo);

    int countReport();
}
