package com.multicampus.gangwonActivity.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multicampus.gangwonActivity.dto.request.weather.WeatherEnum;
import com.multicampus.gangwonActivity.dto.request.weather.WeatherRequestDto;
import com.multicampus.gangwonActivity.entity.Weather;
import com.multicampus.gangwonActivity.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;

    private static final String JSON_KEY = "mzWVwvXH8qzITnKhZ39yP88AfyALFqD5x0iaWepPqERWAT9YivmAxmpgBarKqfOZQdBfSySdtczc%2BTO%2BAFKV0Q%3D%3D";

    /**
     * 전체 날씨 데이터를 저장하는 메인 메소드
     * @return 저장 결과
     * @throws IOException
     */
    public String save() throws IOException {
        WeatherRequestDto weatherRequestDto = new WeatherRequestDto();
        LocalDate startDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String today = startDate.format(formatter);
        String tomorrow = startDate.plusDays(1).format(formatter);

        // 단기 예보 데이터 처리
        processShortTermWeather(today, tomorrow, weatherRequestDto);

        // 중기 일기예보 데이터 처리
        processLongTermWeather(today, weatherRequestDto);

        // 중기 강수 확률 데이터 처리
        processLongTermTemperature(today, weatherRequestDto);

        Weather weather = buildWeatherEntity(weatherRequestDto);
        weatherRepository.save(weather);
        return "done";
    }

    /**
     * 단기 예보 데이터를 처리하는 메소드
     * @param today 오늘 날짜
     * @param tomorrow 내일 날짜
     * @param weatherRequestDto 날씨 데이터 DTO
     * @throws IOException
     */
    private void processShortTermWeather(String today, String tomorrow, WeatherRequestDto weatherRequestDto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String apiUrl = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey="
                + JSON_KEY + "&numOfRows=1000&pageNo=1&dataType=JSON&base_date=" + today + "&base_time=0500&nx=37&ny=128";

        JsonNode jsonNode = getApiResponse(apiUrl);
        JsonNode items = jsonNode.get("response").get("body").get("items").get("item");

        for (JsonNode item : items) {
            String fcstDate = item.path("fcstDate").asText();
            String category = item.path("category").asText();
            String fcstTime = item.path("fcstTime").asText();
            int fcstValue = item.path("fcstValue").asInt();

            if (fcstDate.equals(today)) {
                switch (category) {
                    case "POP":
                        if (fcstTime.equals("1200")) weatherRequestDto.setRnSt1(fcstValue);
                        break;
                    case "TMN":
                        weatherRequestDto.setTaMin1(fcstValue);
                        break;
                    case "TMX":
                        weatherRequestDto.setTaMax1(fcstValue);
                        break;
                    case "SKY":
                        if (fcstTime.equals("1200")) weatherRequestDto.setWf1(WeatherEnum.fromCode(fcstValue));
                        break;
                }
            } else if (fcstDate.equals(tomorrow)) {
                switch (category) {
                    case "POP":
                        if (fcstTime.equals("1200")) weatherRequestDto.setRnSt2(fcstValue);
                        break;
                    case "TMN":
                        weatherRequestDto.setTaMin2(fcstValue);
                        break;
                    case "TMX":
                        weatherRequestDto.setTaMax2(fcstValue);
                        break;
                    case "SKY":
                        if (fcstTime.equals("1200")) weatherRequestDto.setWf2(WeatherEnum.fromCode(fcstValue));
                        break;
                }
            }
        }
    }

    /**
     * 중기 일기예보 데이터를 처리하는 메소드
     * @param today 오늘 날짜
     * @param weatherRequestDto 날씨 데이터 DTO
     * @throws IOException
     */
    private void processLongTermWeather(String today, WeatherRequestDto weatherRequestDto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String apiUrl = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst?serviceKey="
                + JSON_KEY + "&numOfRows=1000&dataType=JSON&pageNo=1&regId=11D20000&tmFc=" + today + "0600";

        JsonNode jsonNode = getApiResponse(apiUrl);
        JsonNode items = jsonNode.get("response").get("body").get("items").get("item");

        for (JsonNode item : items) {
            weatherRequestDto.setRnSt3(item.path("rnSt3Pm").asInt());
            weatherRequestDto.setRnSt4(item.path("rnSt4Pm").asInt());
            weatherRequestDto.setRnSt5(item.path("rnSt5Pm").asInt());
            weatherRequestDto.setRnSt6(item.path("rnSt6Pm").asInt());
            weatherRequestDto.setRnSt7(item.path("rnSt7Pm").asInt());
            weatherRequestDto.setRnSt8(item.path("rnSt8").asInt());
            weatherRequestDto.setRnSt9(item.path("rnSt9").asInt());
            weatherRequestDto.setRnSt10(item.path("rnSt10").asInt());
            weatherRequestDto.setWf3(item.path("wf3Pm").asText());
            weatherRequestDto.setWf4(item.path("wf4Pm").asText());
            weatherRequestDto.setWf5(item.path("wf5Pm").asText());
            weatherRequestDto.setWf6(item.path("wf6Pm").asText());
            weatherRequestDto.setWf7(item.path("wf7Pm").asText());
            weatherRequestDto.setWf8(item.path("wf8").asText());
            weatherRequestDto.setWf9(item.path("wf9").asText());
            weatherRequestDto.setWf10(item.path("wf10").asText());
        }
    }

    /**
     * 중기 기온 데이터를 처리하는 메소드
     * @param today 오늘 날짜
     * @param weatherRequestDto 날씨 데이터 DTO
     * @throws IOException
     */
    private void processLongTermTemperature(String today, WeatherRequestDto weatherRequestDto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String apiUrl = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidTa?serviceKey="
                + JSON_KEY + "&numOfRows=10&dataType=JSON&pageNo=1&regId=11D10401&tmFc=" + today + "0600";

        JsonNode jsonNode = getApiResponse(apiUrl);
        JsonNode items = jsonNode.get("response").get("body").get("items").get("item");

        for (JsonNode item : items) {
            weatherRequestDto.setTaMax3(item.path("taMax3").asInt());
            weatherRequestDto.setTaMin3(item.path("taMin3").asInt());
            weatherRequestDto.setTaMax4(item.path("taMax4").asInt());
            weatherRequestDto.setTaMin4(item.path("taMin4").asInt());
            weatherRequestDto.setTaMax5(item.path("taMax5").asInt());
            weatherRequestDto.setTaMin5(item.path("taMin5").asInt());
            weatherRequestDto.setTaMax6(item.path("taMax6").asInt());
            weatherRequestDto.setTaMin6(item.path("taMin6").asInt());
            weatherRequestDto.setTaMax7(item.path("taMax7").asInt());
            weatherRequestDto.setTaMin7(item.path("taMin7").asInt());
            weatherRequestDto.setTaMax8(item.path("taMax8").asInt());
            weatherRequestDto.setTaMin8(item.path("taMin8").asInt());
            weatherRequestDto.setTaMax9(item.path("taMax9").asInt());
            weatherRequestDto.setTaMin9(item.path("taMin9").asInt());
            weatherRequestDto.setTaMax10(item.path("taMax10").asInt());
            weatherRequestDto.setTaMin10(item.path("taMin10").asInt());
        }
    }

    /**
     * API 응답을 가져오는 메소드
     * @param apiUrl API URL
     * @return JSON 응답
     * @throws IOException
     */
    private JsonNode getApiResponse(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        String result = br.readLine();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(result);
    }

    /**
     * Weather 엔티티를 생성하는 메소드
     * @param dto WeatherRequestDto
     * @return Weather 엔티티
     */
    private Weather buildWeatherEntity(WeatherRequestDto dto) {
        return Weather.builder()
                .taMax1(dto.getTaMax1())
                .taMin1(dto.getTaMin1())
                .taMax2(dto.getTaMax2())
                .taMin2(dto.getTaMin2())
                .taMax3(dto.getTaMax3())
                .taMin3(dto.getTaMin3())
                .taMax4(dto.getTaMax4())
                .taMin4(dto.getTaMin4())
                .taMax5(dto.getTaMax5())
                .taMin5(dto.getTaMin5())
                .taMax6(dto.getTaMax6())
                .taMin6(dto.getTaMin6())
                .taMax7(dto.getTaMax7())
                .taMin7(dto.getTaMin7())
                .taMax8(dto.getTaMax8())
                .taMin8(dto.getTaMin8())
                .taMax9(dto.getTaMax9())
                .taMin9(dto.getTaMin9())
                .taMax10(dto.getTaMax10())
                .taMin10(dto.getTaMin10())
                .rnSt1(dto.getRnSt1())
                .rnSt2(dto.getRnSt2())
                .rnSt3(dto.getRnSt3())
                .rnSt4(dto.getRnSt4())
                .rnSt5(dto.getRnSt5())
                .rnSt6(dto.getRnSt6())
                .rnSt7(dto.getRnSt7())
                .rnSt8(dto.getRnSt8())
                .rnSt9(dto.getRnSt9())
                .rnSt10(dto.getRnSt10())
                .wf1(dto.getWf1())
                .wf2(dto.getWf2())
                .wf3(dto.getWf3())
                .wf4(dto.getWf4())
                .wf5(dto.getWf5())
                .wf6(dto.getWf6())
                .wf7(dto.getWf7())
                .wf8(dto.getWf8())
                .wf9(dto.getWf9())
                .wf10(dto.getWf10())
                .build();
    }
}
