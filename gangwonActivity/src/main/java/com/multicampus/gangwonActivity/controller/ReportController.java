package com.multicampus.gangwonActivity.controller;


import com.multicampus.gangwonActivity.dto.response.report.ReportedContentResponseDto;
import com.multicampus.gangwonActivity.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

}
