package com.multicampus.gangwonActivity.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.multicampus.gangwonActivity.dto.request.api.Tour4_0Dto;
import com.multicampus.gangwonActivity.dto.request.api.GetPlaceCatDto;
import com.multicampus.gangwonActivity.dto.request.api.GetPlaceTitleDto;
import com.multicampus.gangwonActivity.entity.Tour4_0;
import com.multicampus.gangwonActivity.mapper.Tour4_0Mapper;
import com.multicampus.gangwonActivity.repository.Tour4_0Repository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class Tour4_0Service {

    private final Tour4_0Repository tour40Repository;
    private final Tour4_0Mapper tour40Mapper;
    private final AsyncGoogleService asyncGoogleService;

    private double mapX;
    private double mapY;

    @Value("${google.api.key}")
    private String apiKey;
    @Value("${tour.api.key}")
    private String tourApiKey;

    //강원도 API 데이터 호출
    private String getTourData(int pageNo) throws IOException {
        String apiKeyHeader = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=" +
                tourApiKey +
                "&numOfRows=1000" +
                "&pageNo=";
        String cntStr = String.valueOf(pageNo);
        String apiKeyTail = "&MobileOS=ETC" +
                "&MobileApp=AppTest" +
                "&_type=json" +
                "&arrange=A" +
                "&areaCode=32" +
                "&_type=json";
        String apiKey = apiKeyHeader + cntStr + apiKeyTail;

        URL url = new URL(apiKey);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));

        return br.readLine();
    }

    //강원도 API 저장
    public String save() throws IOException {
        int pageNo = 1;

        ObjectMapper objectMapper = new ObjectMapper();

        while (true) {
            String result = getTourData(pageNo);

            // JSON 파싱
            JsonNode jsonNode = objectMapper.readTree(result);

            // response를 받아옴
            JsonNode parseResponse = jsonNode.get("response");

            // body를 받아옴
            JsonNode parseBody = parseResponse.get("body");

            // items를 받아옴
            JsonNode parseItems = parseBody.get("items");

            // item 안쪽의 데이터는 배열의 형태이기에 배열로 받아옴
            JsonNode array = parseItems.get("item");

            if (parseItems.isEmpty()) {
                break;
            }

            pageNo++;

            List<Tour4_0> list = new ArrayList<>();

            for (JsonNode tmp : array) {
                Tour4_0Dto tour40Dto = new Tour4_0Dto();
                tour40Dto.setFirstimage(tmp.path("firstimage").asText(null));
                tour40Dto.setFirstimage2(tmp.path("firstimage2").asText(null));
                tour40Dto.setMapx(tmp.path("mapx").asText(null));
                tour40Dto.setMapy(tmp.path("mapy").asText(null));
                tour40Dto.setAddr2(tmp.path("addr2").asText(null));
                tour40Dto.setCat1(tmp.path("cat1").asText(null));
                tour40Dto.setTel(tmp.path("tel").asText(null));
                tour40Dto.setTitle(tmp.path("title").asText(null));
                tour40Dto.setAddr1(tmp.path("addr1").asText(null));
                tour40Dto.setCat2(tmp.path("cat2").asText(null));
                tour40Dto.setCat3(tmp.path("cat3").asText(null));
                tour40Dto.setContenttypeid(tmp.path("contenttypeid").asText(null));

                Tour4_0 tour40Entity = Tour4_0.builder()
                        .firstImage(tour40Dto.getFirstimage())
                        .firstImage2(tour40Dto.getFirstimage2())
                        .mapx(Double.parseDouble(tour40Dto.getMapx()))
                        .mapy(Double.parseDouble(tour40Dto.getMapy()))
                        .addr1(tour40Dto.getAddr1())
                        .addr2(tour40Dto.getAddr2())
                        .cat1(tour40Dto.getCat1())
                        .tel(tour40Dto.getTel())
                        .placeTitle(tour40Dto.getTitle())
                        .cat2(tour40Dto.getCat2())
                        .cat3(tour40Dto.getCat3())
                        .contenttypeid(Integer.parseInt(tour40Dto.getContenttypeid()))
                        .rate(0.0)
                        .build();
                list.add(tour40Entity);
            }
            tour40Mapper.saveAll(list);

        }
        return "tourApi";
    }

    //구글 API 평점
    public String rating() {
        List<Tour4_0> allEntities = tour40Repository.findAll();
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (Tour4_0 ratelist : allEntities) {
            CompletableFuture<Void> future = asyncGoogleService.fetchRating(ratelist, apiKey)
                    .thenAccept(rating -> {
                        if (rating != null) {
                            ratelist.setRate(rating);
                            tour40Repository.save(ratelist);
                        }
                    });
            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        return "rating";
    }

    //10개의 액티비티
    public List<Tour4_0> getPlace() {
        List<Tour4_0> results = new ArrayList<>();

        results.addAll(tour40Mapper.findEachTwoByRating());

        return results;
    }

    //액티비티 주변 추천 장소
    public String getPlaceTitle(GetPlaceTitleDto placeTitleDto) {
        String title = placeTitleDto.getPlaceTitle();
        mapX = placeTitleDto.getPlaceMapx();
        mapY = placeTitleDto.getPlaceMapy();

        return title;
    }

    //카테고리
    public List<Tour4_0> getPlaceCat(GetPlaceCatDto placeCatDto) {
        String cat2 = placeCatDto.getPlaceCat();

        List<Tour4_0> results = new ArrayList<>();

        if (cat2.equals("restaurant")) {
            results.addAll(tour40Mapper.selectPlaceTitlesWithDistance(mapX, mapY));
        }
        if (cat2.equals("activity")) {
            results.addAll(tour40Mapper.selectPlaceActivityWithDistance(mapX, mapY));
        }
        if (cat2.equals("restaurant")) {
            results.addAll(tour40Mapper.selectPlaceRestaurantWithDistance(mapX, mapY));
        }
        if (cat2.equals("cafe")) {
            results.addAll(tour40Mapper.selectPlaceCafeWithDistance(mapX, mapY));
        }
        if (cat2.equals("tour")) {
            results.addAll(tour40Mapper.selectPlaceTourWithDistance(mapX, mapY));
        }
        if (cat2.equals("accommodation")) {
            results.addAll(tour40Mapper.selectPlaceAccommodationWithDistance(mapX, mapY));

        }

        return results;
    }

}
