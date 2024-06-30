//package com.multicampus.ganwonActivity.controller;
//
//import com.multicampus.ganwonActivity.dto.response.api.PlaceApiResponseDto;
//import com.multicampus.ganwonActivity.service.implement.PlaceService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/getjson")
//public class ApiController {
//
//    private final PlaceService placeService;
//
//
//    @PostMapping("")
//    public ResponseEntity<? super PlaceApiResponseDto> fetchDataFromApi()  {
//        placeService.fetchDataFromApi();
//        PlaceApiResponseDto response = new PlaceApiResponseDto();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//}
//
//
