package com.multicampus.ganwonActivity.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "place")
@Table(name = "place")
@Builder
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeNo;
    private String placeTitle;
    private String addr1;
    private String addr2;
    private String cat1;
    private String cat2;
    private String cat3;
    private Integer contentTypeId;
    private String firstImage;
    private String firstImage2;
    private Double mapx;
    private Double mapy;
    private String tel;
    private Double rate;





}
