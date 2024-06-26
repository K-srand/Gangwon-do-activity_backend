package com.multicampus.ganwonActivity.service.implement;

import com.multicampus.ganwonActivity.dto.request.api.PlaceApiRequestDto;
import com.multicampus.ganwonActivity.dto.response.api.PlaceApiResponseDto;
import com.multicampus.ganwonActivity.entity.PlaceEntity;
import com.multicampus.ganwonActivity.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceService {
    @Autowired
    private final PlaceRepository placeRepository;

    RestTemplate restTemplate = new RestTemplate();
    @Transactional
    public void fetchDataFromApi() {
        String apiUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=mzWVwvXH8qzITnKhZ39yP88AfyALFqD5x0iaWepPqERWAT9YivmAxmpgBarKqfOZQdBfSySdtczc%2BTO%2BAFKV0Q%3D%3D&pageNo=1&numOfRows=100&MobileApp=AppTest&MobileOS=ETC&arrange=A&areaCode=32&_type=json";



        restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request,body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });


//            PlaceApiRequestDto request = restTemplate.getForObject(apiUrl, PlaceApiRequestDto.class);
            final HttpHeaders headers = new HttpHeaders();
            final HttpEntity<?> entity = new HttpEntity<>(headers);

        PlaceApiRequestDto request = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, PlaceApiRequestDto.class).getBody();
        List<PlaceEntity> placeEntityList = new ArrayList<>();

        assert request != null;
            for (PlaceApiRequestDto.Item i: request.getResponse().getBody().getItems().getItem()) {
                PlaceEntity placeEntity = PlaceEntity.builder()
                        .placeTitle(i.getTitle())
                        .addr1(i.getAddr1())
                        .addr2(i.getAddr2())
                        .cat1(i.getCat1())
                        .cat2(i.getCat2())
                        .cat3(i.getCat3())
                        .contentTypeId(i.getContenttypeid())
                        .firstImage(i.getFirstimage())
                        .firstImage2(i.getFirstimage2())
                        .mapx(i.getMapx())
                        .mapy(i.getMapy())
                        .tel(i.getTel())
                        .build();
                placeEntityList.add(placeEntity);
            }

        placeRepository.saveAll(placeEntityList);
    }
}

