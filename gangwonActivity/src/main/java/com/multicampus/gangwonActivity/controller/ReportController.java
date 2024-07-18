package com.multicampus.gangwonActivity.controller;


import com.multicampus.gangwonActivity.dto.response.board.DeleteBoardResponseDto;
import com.multicampus.gangwonActivity.dto.response.report.ReportListResponseDto;
import com.multicampus.gangwonActivity.dto.response.report.ReportedContentResponseDto;
import com.multicampus.gangwonActivity.entity.ReportedContent;
import com.multicampus.gangwonActivity.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
public class ReportController {

    private final ReportService reportService;


    @PostMapping("/board/{boardNo}")
    public ResponseEntity<? super ReportedContentResponseDto> reportBoard(
            @AuthenticationPrincipal String id,
            @PathVariable("boardNo") Long boardNo

    ){

        ResponseEntity<? super ReportedContentResponseDto> response =
                reportService.reportBoard(boardNo,id);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/comment/{commentNo}")
    public ResponseEntity<? super ReportedContentResponseDto> reportComment(
            @AuthenticationPrincipal String id,
            @PathVariable("commentNo") Long commentNo

    ){

        ResponseEntity<? super ReportedContentResponseDto> response =
                reportService.reportComment(commentNo,id);

        return ResponseEntity.ok(response);
    }

    //reportList
    @GetMapping("/")
    public ResponseEntity<List<ReportListResponseDto>> listReport() {
        List<ReportListResponseDto> reportList = reportService.listReport();
        return ResponseEntity.ok(reportList);
    }

    //delete
    @PatchMapping("/delete/{reportedContentNo}")
    public ResponseEntity<Void> deleteReport(@PathVariable("reportedContentNo") Long reportedContentNo) {
        reportService.reportDelete(reportedContentNo);
        return ResponseEntity.ok().build();
    }

}
