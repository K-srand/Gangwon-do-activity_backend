package com.multicampus.gangwonActivity.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "myFavoritesMappingTable")
@Table(name = "myFavoritesMappingTable")
@Builder
public class MyFavoritesEntity {
    @Id

    @Column(name="userNo")
    private Long userNo;

    @Column(name="placeNo")
    private Long placeNo;

    //firstImage
    @Column(name = "firstImage")
    private String firstImage;

    //firstImage2
    @Column(name = "firstImage2")
    private String firstImage2;

    //mapx
    @Column(name = "mapx")
    private Double mapx;

    //mapy
    @Column(name = "mapy")
    private Double mapy;

    //cat1
    @Column(name = "cat1")
    private String cat1;

    //cat3
    @Column(name = "cat3")
    private String cat3;

    //title
    @Column(name = "placeTitle")
    private String placeTitle;


}
