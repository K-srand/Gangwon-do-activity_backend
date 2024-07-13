package com.multicampus.gangwonActivity.controller;

import com.multicampus.gangwonActivity.dto.request.api.GetNaverMapDto;
import com.multicampus.gangwonActivity.service.NaverMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NaverMapController {

    private final NaverMapService naverMapService;

    //네이버 map API 서비스 호출
    @GetMapping("/api/v1/getmap")
    public String getMapScript() {
        return naverMapService.getNaverMapScript();
    }

    //소요시간 서비스 호출
    @PostMapping("/api/v1/getdrive")
    public String getDrive(@RequestBody GetNaverMapDto getNaverMapDto) {
        return naverMapService.getDrivingDuration(getNaverMapDto);
    }
}
