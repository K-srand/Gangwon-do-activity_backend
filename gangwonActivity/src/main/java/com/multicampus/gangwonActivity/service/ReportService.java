package com.multicampus.gangwonActivity.service;


import com.multicampus.gangwonActivity.dto.response.report.ReportedContentResponseDto;
//import com.multicampus.gangwonActivity.entity.ReportRequestDto;
import org.springframework.http.ResponseEntity;

public interface ReportService {

    ResponseEntity<? super ReportedContentResponseDto> reportBoard( Long boardNo, String id);
    ResponseEntity<? super ReportedContentResponseDto> reportComment(Long commentNo, String id);


}
