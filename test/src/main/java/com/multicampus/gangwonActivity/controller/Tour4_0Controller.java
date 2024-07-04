package com.multicampus.gangwonActivity.controller;

import com.multicampus.gangwonActivity.dto.request.GetPlaceCatDto;
import com.multicampus.gangwonActivity.dto.request.GetPlaceTitleDto;
import com.multicampus.gangwonActivity.entity.Tour4_0Entity;
import com.multicampus.gangwonActivity.service.Tour4_0Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/getjson")
@RestController
@RequiredArgsConstructor
public class Tour4_0Controller {
    private final Tour4_0Service tour40Service;

    @GetMapping("/save")
    public String getJson() throws IOException, InterruptedException {
        return tour40Service.save();
    }

    @GetMapping("/rating")
    public String getApi() throws IOException, InterruptedException {
        return tour40Service.rating();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getplace")
    public List<Tour4_0Entity> getTop2PlaceByCat2() {
        return tour40Service.getPlace();
    }


    @PostMapping("/getplacetitle")
    public String receivePlaceTitle(@RequestBody GetPlaceTitleDto getPlaceTitleDto) {
        return tour40Service.getPlaceTitle(getPlaceTitleDto);
    }

    @PostMapping("/getplacecat")
    public List<Tour4_0Entity> receivePlaceCat2(@RequestBody GetPlaceCatDto getPlaceCatDto) {
        return tour40Service.getPlaceCat(getPlaceCatDto);
    }
}
