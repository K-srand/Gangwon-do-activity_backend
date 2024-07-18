package com.multicampus.gangwonActivity.controller;


import com.multicampus.gangwonActivity.dto.response.board.DeleteBoardResponseDto;
import com.multicampus.gangwonActivity.dto.response.board.SearchPageDto;
import com.multicampus.gangwonActivity.dto.response.report.ReportListResponseDto;
import com.multicampus.gangwonActivity.dto.response.report.ReportedContentResponseDto;
import com.multicampus.gangwonActivity.entity.ReportedContent;
import com.multicampus.gangwonActivity.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<PageImpl<ReportListResponseDto>> listReport(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {

        SearchPageDto searchPageDto = new SearchPageDto();
        searchPageDto.setPage(page);
        searchPageDto.setSize(size);

        List<ReportListResponseDto> reportList = reportService.listReport(searchPageDto);
        int reportCount = reportService.countReport();

        return ResponseEntity.ok(
            new PageImpl<>(reportList, PageRequest.of(searchPageDto.getPage(), searchPageDto.getSize()), reportCount));
    }

    //delete
    @DeleteMapping ("/delete/{reportedContentNo}")
    public ResponseEntity<Void> deleteReport(@PathVariable("reportedContentNo") Long reportedContentNo) {
        reportService.reportDelete(reportedContentNo);
        return ResponseEntity.ok().build();
    }

}
