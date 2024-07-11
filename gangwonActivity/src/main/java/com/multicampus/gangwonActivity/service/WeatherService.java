package com.multicampus.gangwonActivity.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multicampus.gangwonActivity.dto.request.weather.WeatherEnum;
import com.multicampus.gangwonActivity.dto.request.weather.WeatherRequestDto;
import com.multicampus.gangwonActivity.entity.WeatherEntity;
import com.multicampus.gangwonActivity.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;

//    @Value("${json-key}")
//    private final String jsonKey;

    public String save() throws IOException, InterruptedException {


        String jsonKey = "mzWVwvXH8qzITnKhZ39yP88AfyALFqD5x0iaWepPqERWAT9YivmAxmpgBarKqfOZQdBfSySdtczc%2BTO%2BAFKV0Q%3D%3D";

        WeatherRequestDto weatherRequestDto = new WeatherRequestDto();

        LocalDate startDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String today = startDate.format(formatter);
        String tomorrow = startDate.plusDays(1).format(formatter);
        String yesterday = startDate.minusDays(1).format(formatter);

        ObjectMapper objectMapperShort = new ObjectMapper();
        //단기 예보 받아오기
        String shortApiKeyHeader = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=";
        String shortApiKeyBody = "&numOfRows=1000&pageNo=1&dataType=JSON&base_date=";
        String shortApiKeyTail = "&base_time=0500&nx=37&ny=128";

        String shortApiKey = shortApiKeyHeader + jsonKey + shortApiKeyBody + today + shortApiKeyTail;

        URL url = new URL(shortApiKey);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        String result = br.readLine();


        //Json 파싱 단계
        JsonNode jsonNode = objectMapperShort.readTree(result);

        // response를 받아옴
        JsonNode parseResponse = jsonNode.get("response");

        // body를 받아옴
        JsonNode parseBody = parseResponse.get("body");

        // items를 받아옴
        JsonNode parseItems = parseBody.get("items");

        // item 안쪽의 데이터는 배열의 형태이기에 배열로 받아옴
        JsonNode array = parseItems.get("item");


        //맑음(1), 구름많음(3), 흐림(4)

        for (JsonNode tmp : array) {
            if (tmp.path("fcstDate").asText(null).equals(today)
                    && tmp.path("category").asText().equals("POP")&& tmp.path("fcstTime").asText().equals("1200")) {
                weatherRequestDto.setRnSt1(tmp.path("fcstValue").asInt());
            } else if (tmp.path("fcstDate").asText(null).equals(today)
                    && tmp.path("category").asText().equals("TMN")) {
                weatherRequestDto.setTaMin1(tmp.path("fcstValue").asInt());
            } else if (tmp.path("fcstDate").asText(null).equals(today)
                    && tmp.path("category").asText().equals("TMX")) {
                weatherRequestDto.setTaMax1(tmp.path("fcstValue").asInt());
            } else if (tmp.path("fcstDate").asText(null).equals(tomorrow)
                    && tmp.path("category").asText().equals("POP")&& tmp.path("fcstTime").asText().equals("1200")) {
                weatherRequestDto.setRnSt2(tmp.path("fcstValue").asInt());
            } else if (tmp.path("fcstDate").asText(null).equals(tomorrow)
                    && tmp.path("category").asText().equals("TMN")) {
                weatherRequestDto.setTaMin2(tmp.path("fcstValue").asInt());
            } else if (tmp.path("fcstDate").asText(null).equals(tomorrow)
                    && tmp.path("category").asText().equals("TMX")) {
                weatherRequestDto.setTaMax2(tmp.path("fcstValue").asInt());
            } else if (tmp.path("fcstDate").asText().equals(today)
                    && tmp.path("category").asText().equals("SKY")&& tmp.path("fcstTime").asText().equals("1200")) {
                int skyCode = tmp.path("fcstValue").asInt();
                if (skyCode == 1 || skyCode == 3 || skyCode == 4) {
                    weatherRequestDto.setWf1(WeatherEnum.fromCode(skyCode));
                } else {
                    weatherRequestDto.setWf1("흐리고 비");
                }
            } else if (tmp.path("fcstDate").asText().equals(tomorrow)
                    && tmp.path("category").asText().equals("SKY")&& tmp.path("fcstTime").asText().equals("1200")) {
                int skyCode = tmp.path("fcstValue").asInt();
                if (skyCode == 1 || skyCode == 3 || skyCode == 4) {
                    weatherRequestDto.setWf2(WeatherEnum.fromCode(skyCode));
                } else {
                    weatherRequestDto.setWf2("흐리고 비");
                }
            }
        }
        System.out.println(weatherRequestDto);
        //중기 일기예보 데이터들


        ObjectMapper objectMapperLong = new ObjectMapper();

        String longWeatherApiKeyHeader = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst?serviceKey=";
        String longWeatherApiKeyBody = "&numOfRows=1000&dataType=JSON&pageNo=1&regId=11D20000&tmFc=";
        String foreCastTime = "0600";
        String longApiKey = longWeatherApiKeyHeader + jsonKey + longWeatherApiKeyBody  + today + foreCastTime;

        URL urlLong = new URL(longApiKey);
        br = new BufferedReader(new InputStreamReader(urlLong.openStream(), "UTF-8"));
        result = br.readLine();

        jsonNode = objectMapperShort.readTree(result);
        parseResponse = jsonNode.get("response");
        parseBody = parseResponse.get("body");
        parseItems = parseBody.get("items");
        array = parseItems.get("item");


        for(JsonNode tmp : array){
            weatherRequestDto.setRnSt3(tmp.path("rnSt3Pm").asInt());
            weatherRequestDto.setRnSt4(tmp.path("rnSt4Pm").asInt());
            weatherRequestDto.setRnSt5(tmp.path("rnSt5Pm").asInt());
            weatherRequestDto.setRnSt6(tmp.path("rnSt6Pm").asInt());
            weatherRequestDto.setRnSt7(tmp.path("rnSt7Pm").asInt());
            weatherRequestDto.setRnSt8(tmp.path("rnSt8").asInt());
            weatherRequestDto.setRnSt9(tmp.path("rnSt9").asInt());
            weatherRequestDto.setRnSt10(tmp.path("rnSt10").asInt());
            weatherRequestDto.setWf3(tmp.path("wf3Pm").asText(null));
            weatherRequestDto.setWf4(tmp.path("wf4Pm").asText(null));
            weatherRequestDto.setWf5(tmp.path("wf5Pm").asText(null));
            weatherRequestDto.setWf6(tmp.path("wf6Pm").asText(null));
            weatherRequestDto.setWf7(tmp.path("wf7Pm").asText(null));
            weatherRequestDto.setWf8(tmp.path("wf8").asText(null));
            weatherRequestDto.setWf9(tmp.path("wf9").asText(null));
            weatherRequestDto.setWf10(tmp.path("wf10").asText(null));

        }
        ObjectMapper objectMapperLongTmp = new ObjectMapper();

        String longTmpApiKeyHeader = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa?serviceKey=";
        String longTmpApiKeyBody = "&numOfRows=10&dataType=JSON&pageNo=1&regId=11D10401&tmFc=";
        foreCastTime = "0600";
        longApiKey = longTmpApiKeyHeader + jsonKey + longTmpApiKeyBody + today + foreCastTime;

        urlLong = new URL(longApiKey);
        br = new BufferedReader(new InputStreamReader(urlLong.openStream(), "UTF-8"));
        result = br.readLine();

        jsonNode = objectMapperShort.readTree(result);
        parseResponse = jsonNode.get("response");
        parseBody = parseResponse.get("body");
        parseItems = parseBody.get("items");
        array = parseItems.get("item");


        for(JsonNode tmp : array){
            System.out.println(tmp);
            weatherRequestDto.setTaMax3(tmp.path("taMax3").asInt());
            weatherRequestDto.setTaMin3(tmp.path("taMin3").asInt());
            weatherRequestDto.setTaMax4(tmp.path("taMax4").asInt());
            weatherRequestDto.setTaMin4(tmp.path("taMin4").asInt());
            weatherRequestDto.setTaMax5(tmp.path("taMax5").asInt());
            weatherRequestDto.setTaMin5(tmp.path("taMin5").asInt());
            weatherRequestDto.setTaMax6(tmp.path("taMax6").asInt());
            weatherRequestDto.setTaMin6(tmp.path("taMin6").asInt());
            weatherRequestDto.setTaMax7(tmp.path("taMax7").asInt());
            weatherRequestDto.setTaMin7(tmp.path("taMin7").asInt());
            weatherRequestDto.setTaMax8(tmp.path("taMax8").asInt());
            weatherRequestDto.setTaMin8(tmp.path("taMin8").asInt());
            weatherRequestDto.setTaMax9(tmp.path("taMax9").asInt());
            weatherRequestDto.setTaMin9(tmp.path("taMin9").asInt());
            weatherRequestDto.setTaMax10(tmp.path("taMax10").asInt());
            weatherRequestDto.setTaMin10(tmp.path("taMin10").asInt());
        }


        WeatherEntity weatherEntity = WeatherEntity.builder()
                .taMax1(weatherRequestDto.getTaMax1())
                .taMin1(weatherRequestDto.getTaMin1())
                .taMax2(weatherRequestDto.getTaMax2())
                .taMin2(weatherRequestDto.getTaMin2())
                .taMax3(weatherRequestDto.getTaMax3())
                .taMin3(weatherRequestDto.getTaMin3())
                .taMax4(weatherRequestDto.getTaMax4())
                .taMin4(weatherRequestDto.getTaMin4())
                .taMax5(weatherRequestDto.getTaMax5())
                .taMin5(weatherRequestDto.getTaMin5())
                .taMax6(weatherRequestDto.getTaMax6())
                .taMin6(weatherRequestDto.getTaMin6())
                .taMax7(weatherRequestDto.getTaMax7())
                .taMin7(weatherRequestDto.getTaMin7())
                .taMax8(weatherRequestDto.getTaMax8())
                .taMin8(weatherRequestDto.getTaMin8())
                .taMax9(weatherRequestDto.getTaMax9())
                .taMin9(weatherRequestDto.getTaMin9())
                .taMax10(weatherRequestDto.getTaMax10())
                .taMin10(weatherRequestDto.getTaMin10())
                .rnSt1(weatherRequestDto.getRnSt1())
                .rnSt2(weatherRequestDto.getRnSt2())
                .rnSt3(weatherRequestDto.getRnSt3())
                .rnSt4(weatherRequestDto.getRnSt4())
                .rnSt5(weatherRequestDto.getRnSt5())
                .rnSt6(weatherRequestDto.getRnSt6())
                .rnSt7(weatherRequestDto.getRnSt7())
                .rnSt8(weatherRequestDto.getRnSt8())
                .rnSt9(weatherRequestDto.getRnSt9())
                .rnSt10(weatherRequestDto.getRnSt10())
                .wf1(weatherRequestDto.getWf1())
                .wf2(weatherRequestDto.getWf2())
                .wf3(weatherRequestDto.getWf3())
                .wf4(weatherRequestDto.getWf4())
                .wf5(weatherRequestDto.getWf5())
                .wf6(weatherRequestDto.getWf6())
                .wf7(weatherRequestDto.getWf7())
                .wf8(weatherRequestDto.getWf8())
                .wf9(weatherRequestDto.getWf9())
                .wf10(weatherRequestDto.getWf10())
                .build();

        weatherRepository.save(weatherEntity);
        return "done";
    }
}