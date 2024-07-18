package com.multicampus.gangwonActivity.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "weather")
@Table(name = "weather")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //=auto_Increment
    @Column(name="weatherNo")
    private Long weatherNo;

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


    public Integer getTaMax(int day) {
        switch (day) {
            case 1: return taMax1;
            case 2: return taMax2;
            case 3: return taMax3;
            case 4: return taMax4;
            case 5: return taMax5;
            case 6: return taMax6;
            case 7: return taMax7;
            case 8: return taMax8;
            case 9: return taMax9;
            case 10: return taMax10;
            default: return null;
        }
    }

    public Integer getTaMin(int day) {
        switch (day) {
            case 1: return taMin1;
            case 2: return taMin2;
            case 3: return taMin3;
            case 4: return taMin4;
            case 5: return taMin5;
            case 6: return taMin6;
            case 7: return taMin7;
            case 8: return taMin8;
            case 9: return taMin9;
            case 10: return taMin10;
            default: return null;
        }
    }

    public Integer getRnSt(int day) {
        switch (day) {
            case 1: return rnSt1;
            case 2: return rnSt2;
            case 3: return rnSt3;
            case 4: return rnSt4;
            case 5: return rnSt5;
            case 6: return rnSt6;
            case 7: return rnSt7;
            case 8: return rnSt8;
            case 9: return rnSt9;
            case 10: return rnSt10;
            default: return null;
        }
    }

    public String getWf(int day) {
        switch (day) {
            case 1: return wf1;
            case 2: return wf2;
            case 3: return wf3;
            case 4: return wf4;
            case 5: return wf5;
            case 6: return wf6;
            case 7: return wf7;
            case 8: return wf8;
            case 9: return wf9;
            case 10: return wf10;
            default: return null;
        }
    }
}
