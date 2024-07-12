package com.multicampus.gangwonActivity.controller;

import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyCourseDto;
import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyCourseMappingDto;
import com.multicampus.gangwonActivity.dto.request.mycourse.GetMyFavoritesDto;
import com.multicampus.gangwonActivity.entity.MyCourseMappingEntity;
import com.multicampus.gangwonActivity.entity.MyFavoritesEntity;
import com.multicampus.gangwonActivity.service.CreateMyCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/getcourse")
    public String saveCourse(@RequestBody GetMyCourseDto getMyCourseDto) {
        return createMyCourseService.getMyCourse(getMyCourseDto);
    }
}
