package com.multicampus.gangwonActivity.controller;


import com.multicampus.gangwonActivity.dto.request.api.GetPlaceCatDto;
import com.multicampus.gangwonActivity.dto.request.api.GetPlaceTitleDto;

import com.multicampus.gangwonActivity.entity.Tour4_0;
import com.multicampus.gangwonActivity.service.Tour4_0Service;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/getjson")
@RestController
@RequiredArgsConstructor
public class Tour4_0Controller {
    private final Tour4_0Service tour40Service;

    //투어 API 서비스 호출
    @Scheduled(cron = "0 0 0 * * MON")
    @GetMapping("/save")
    public String getJson() throws IOException, InterruptedException {
        return tour40Service.save();
    }

    //구글 평점 API 서비스 호출
    @Scheduled(cron = "0 10 0 * * MON")
    @GetMapping("/rating")
    public String getApi() throws IOException, InterruptedException {
        return tour40Service.rating();
    }

    //추천 10개 액티비티 서비스 호출
    @GetMapping("/getplace")
    public List<Tour4_0> getTop2PlaceByCat2() {
        return tour40Service.getPlace();
    }

    //플레이스 타이틀 서비스 호출
    @PostMapping("/getplacetitle")
    public String receivePlaceTitle(@RequestBody GetPlaceTitleDto getPlaceTitleDto) {
        return tour40Service.getPlaceTitle(getPlaceTitleDto);
    }

    //플레이스 카테고리 서비스 호출
    @PostMapping("/getplacecat")
    public List<Tour4_0> receivePlaceCat(@RequestBody GetPlaceCatDto getPlaceCatDto) {
        return tour40Service.getPlaceCat(getPlaceCatDto);
    }
}
