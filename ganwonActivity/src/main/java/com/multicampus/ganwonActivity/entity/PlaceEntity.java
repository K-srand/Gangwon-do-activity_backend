package com.multicampus.ganwonActivity.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "place")
@Table(name = "place")
@Builder
@Data
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

    @Builder
    public PlaceEntity(String placeTitle, String addr1, String addr2, String cat1, String cat2, String cat3, int contentTypeId, String firstImage, String firstImage2, double mapx, double mapy, String tel) {
        this.placeTitle = placeTitle;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.cat1 = cat1;
        this.cat2 = cat2;
        this.cat3 = cat3;
        this.contentTypeId = contentTypeId;
        this.firstImage = firstImage;
        this.firstImage2 = firstImage2;
        this.mapx = mapx;
        this.mapy = mapy;
        this.tel = tel;
    }



}
