package com.multicampus.gangwonActivity.controller;

import com.multicampus.gangwonActivity.dto.request.GetMyFavoritesDto;
import com.multicampus.gangwonActivity.dto.request.GetPlaceCatDto;
import com.multicampus.gangwonActivity.entity.MyFavoritesEntity;
import com.multicampus.gangwonActivity.entity.Tour4_0Entity;
import com.multicampus.gangwonActivity.service.CreateMyCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/getmycourse")
@RestController
@RequiredArgsConstructor
public class CreateMyCourseController {
    private final CreateMyCourseService createMyCourseService;


    @PostMapping("/getplacecat")
    public List<MyFavoritesEntity> receivePlaceCat(@RequestBody GetMyFavoritesDto getMyFavoritesDto) {
        return createMyCourseService.getPlaceCat(getMyFavoritesDto);
    }
}
