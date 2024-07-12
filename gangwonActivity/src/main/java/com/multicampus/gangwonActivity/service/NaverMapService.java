package com.multicampus.gangwonActivity.service;

import com.multicampus.gangwonActivity.dto.request.api.GetNaverMapDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NaverMapService {

    private final String apiUrl = "https://oapi.map.naver.com/openapi/v3/maps.js";
    private final String directionsApiUrl = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving";

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    public String getNaverMapScript() {
        String fullUrl = apiUrl + "?ncpClientId=" + clientId + "&submodules=geocoder";
        return fullUrl;
    }

    public String getDrivingDuration(GetNaverMapDto getNaverMapDto) {
        RestTemplate restTemplate = new RestTemplate();
        String url = directionsApiUrl + "?start=" + getNaverMapDto.getStartLng() + "," + getNaverMapDto.getStartLat() + "&goal="
                + getNaverMapDto.getEndLng() + "," + getNaverMapDto.getEndLat();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
        headers.set("X-NCP-APIGW-API-KEY", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();  // You'll need to parse the response to get the time
    }
}
