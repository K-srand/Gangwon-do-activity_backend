package com.multicampus.gangwonActivity.controller;

import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyCourseDto;
import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyFavoritesDto;
import com.multicampus.gangwonActivity.entity.MyFavoritesUserPlace;
import com.multicampus.gangwonActivity.service.implement.CreateMyCourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/getmycourse")
@RestController
@RequiredArgsConstructor
public class CreateMyCourseController {
    private final CreateMyCourseServiceImpl createMyCourseServiceImpl;

    //전체 플레이스 서비스 호출
    @PostMapping("/getplacetotal")
    public List<MyFavoritesUserPlace> receivePlaceTotal(@AuthenticationPrincipal String id) {
        return createMyCourseServiceImpl.getPlaceTotal(id);
    }

    //카테고리별 플레이스 서비스 호출
    @PostMapping("/getplacecat")
    public List<MyFavoritesUserPlace> receivePlaceCat(@RequestBody GetMyFavoritesDto getMyFavoritesDto) {
        return createMyCourseServiceImpl.getPlaceCat(getMyFavoritesDto);
    }

    //코스 저장 서비스 호출
    @PostMapping("/getcourse")
    public ResponseEntity<?> saveCourse(@RequestBody GetMyCourseDto getMyCourseDto) {
        return createMyCourseServiceImpl.getMyCourse(getMyCourseDto);
    }
}
