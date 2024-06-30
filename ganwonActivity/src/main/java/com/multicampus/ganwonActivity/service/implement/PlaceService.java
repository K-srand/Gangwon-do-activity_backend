//package com.multicampus.ganwonActivity.service.implement;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.multicampus.ganwonActivity.dto.request.api.PlaceApiRequestDto;
//import com.multicampus.ganwonActivity.dto.response.api.PlaceApiResponseDto;
//import com.multicampus.ganwonActivity.entity.PlaceEntity;
//import com.multicampus.ganwonActivity.repository.PlaceRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestClient;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class PlaceService {
//    @Autowired
//    private final PlaceRepository placeRepository;
//
//    @Autowired
//    RestTemplate restTemplate = new RestTemplate();
//    RestClient restClient = RestClient.create(restTemplate);
//
//    @Transactional
//    public void fetchDataFromApi() {
//        String apiUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=mzWVwvXH8qzITnKhZ39yP88AfyALFqD5x0iaWepPqERWAT9YivmAxmpgBarKqfOZQdBfSySdtczc%2BTO%2BAFKV0Q%3D%3D&pageNo=1&numOfRows=100&MobileApp=AppTest&MobileOS=ETC&arrange=A&areaCode=32&_type=json";

//        try {
//            restClient.getInterceptors().add((request, body, execution) -> {
//                ClientHttpResponse response = execution.execute(request,body);
//                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//                return response;
//            });

//            final HttpHeaders headers = new HttpHeaders();
//            final HttpEntity<?> entity = new HttpEntity<>(headers);
//
//            ResponseEntity<String> response = restClient.exchange(apiUrl, HttpMethod.POST, entity, String.class);
//
//
//            // JSON 파싱
//            ObjectMapper objectMapper = new ObjectMapper();
//            PlaceApiRequestDto request = objectMapper.readValue(response.getBody(), PlaceApiRequestDto.class);
//
//            if (request != null && request.getResponse() != null && request.getResponse().getBody() != null && request.getResponse().getBody().getItems() != null) {
//                List<PlaceApiRequestDto.Item> items = request.getResponse().getBody().getItems().getItem();
//                List<PlaceEntity> placeEntityList = new ArrayList<>();
//
//                for (PlaceApiRequestDto.Item i : items) {
//                    PlaceEntity placeEntity = PlaceEntity.builder()
//                            .placeTitle(i.getTitle())
//                            .addr1(i.getAddr1())
//                            .addr2(i.getAddr2())
//                            .cat1(i.getCat1())
//                            .cat2(i.getCat2())
//                            .cat3(i.getCat3())
//                            .contentTypeId(i.getContentTypeid())
//                            .firstImage(i.getFirstimage())
//                            .firstImage2(i.getFirstimage2())
//                            .mapx(i.getMapx())
//                            .mapy(i.getMapy())
//                            .tel(i.getTel())
//                            .build();
//                    placeEntityList.add(placeEntity);
//                }
//
//                placeRepository.saveAll(placeEntityList);
//            } else {
//                System.out.println("No data received or validation failed");
//            }
//        } catch (HttpClientErrorException e) {
//            // HTTP 오류 처리 (예: 4xx, 5xx 오류)
//            System.err.println("HTTP error: " + e.getStatusCode());
//            System.err.println("Response body: " + e.getResponseBodyAsString());
//        } catch (Exception e) {
//            // 기타 오류 처리
//            e.printStackTrace();
//        }
//    }
//}

