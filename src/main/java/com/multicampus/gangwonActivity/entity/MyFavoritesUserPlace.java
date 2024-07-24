package com.multicampus.gangwonActivity.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "myFavoritesUserPlace")
@Table(name = "myFavoritesUserPlace")
@Builder
public class MyFavoritesUserPlace {
    @Id

    @Column(name="userNo")
    private Long userNo;

    @Column(name="placeNo")
    private Long placeNo;

}
