package com.multicampus.gangwonActivity.dto.request.weather;

public enum WeatherEnum {
    맑음(1),
    구름많음(3),
    흐림(4);

    private final int code;

    WeatherEnum(int code){
        this.code=code;
    }

    public int getCode(){
        return  code;
    }
    public static String fromCode(int code) {
        for (WeatherEnum weather : WeatherEnum.values()) {
            if (weather.getCode() == code) {
                return weather.name();
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
