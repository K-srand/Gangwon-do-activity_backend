package com.multicampus.gangwonActivity.dto.request.weather;

import lombok.Data;

@Data
public class WeatherRequestDto {

    //최대 및 최소 기온
    private Integer taMax1;
    private Integer taMin1;
    private Integer taMax2;
    private Integer taMin2;
    private Integer taMin3;
    private Integer taMax3;
    private Integer taMin4;
    private Integer taMax4;
    private Integer taMax5;
    private Integer taMin5;
    private Integer taMax6;
    private Integer taMin6;
    private Integer taMax7;
    private Integer taMin7;
    private Integer taMax8;
    private Integer taMin8;
    private Integer taMax9;
    private Integer taMin9;
    private Integer taMax10;
    private Integer taMin10;

    //강수확률
    private Integer rnSt1;
    private Integer rnSt2;
    private Integer rnSt3;
    private Integer rnSt4;
    private Integer rnSt5;
    private Integer rnSt6;
    private Integer rnSt7;
    private Integer rnSt8;
    private Integer rnSt9;
    private Integer rnSt10;

    //날씨
    private String wf1;
    private String wf2;
    private String wf3;
    private String wf4;
    private String wf5;
    private String wf6;
    private String wf7;
    private String wf8;
    private String wf9;
    private String wf10;
}
