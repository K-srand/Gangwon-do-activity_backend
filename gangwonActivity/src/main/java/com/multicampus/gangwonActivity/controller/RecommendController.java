package com.multicampus.gangwonActivity.controller;

import com.multicampus.gangwonActivity.dto.response.board.GetBoardListResponseDto;
import com.multicampus.gangwonActivity.dto.response.recommend.BestMyCourseDto;
import com.multicampus.gangwonActivity.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    @PostMapping("")
    public ResponseEntity<?> recommendInfo(){

//        recommendService.getCourseInfo();
        List<BestMyCourseDto> BestMyCourseDto = recommendService.getBestInfo();
        return ResponseEntity.ok(BestMyCourseDto);
    }

    @GetMapping("/{myCourseNo}")
    public ResponseEntity<?> myCourseInfo(@PathVariable("myCourseNo")Long myCourseNo){
        List<Map<String, Object>> list = recommendService.getCourseInfo(myCourseNo);
        return ResponseEntity.ok(list);
    }

}
