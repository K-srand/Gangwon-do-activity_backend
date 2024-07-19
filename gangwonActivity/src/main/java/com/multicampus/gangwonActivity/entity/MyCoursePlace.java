package com.multicampus.gangwonActivity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "myCoursePlace")
@Table(name = "myCoursePlace")
@Builder
public class MyCoursePlace {

    @Id

    @Column(name = "myCourseNo")
    private Long myCourseNo;

    @Column(name = "placeNo")
    private Long placeNo;

    @Column(name = "orderNo")
    private Integer orderNo;

    @Column(name = "writtenTime")
    private LocalDateTime writtenTime;
}
