package com.multicampus.gangwonActivity.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "place")
@Table(name = "place")
@Builder
public class Tour4_0Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //=auto_Increment
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

    //addr1
    @Column(name = "addr1")
    private String addr1;

    //addr2
    @Column(name = "addr2")
    private String addr2;

    //cat1
    @Column(name = "cat1")
    private String cat1;

    //cat2
    @Column(name = "cat2")
    private String cat2;

    //cat3
    @Column(name = "cat3")
    private String cat3;

    //tel
    @Column(name = "tel")
    private String tel;

    //title
    @Column(name = "placeTitle")
    private String placeTitle;

    //contenttypeid
    @Column(name = "contentTypeId")
    private Integer contenttypeid;

    //Rating
    @Column(name = "rate")
    private Double rate;

}
