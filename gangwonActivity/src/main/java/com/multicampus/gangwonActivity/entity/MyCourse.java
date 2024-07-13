package com.multicampus.gangwonActivity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "myCourse")
@Table(name = "myCourse")
@Builder
public class MyCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //=auto_Increment
    @Column(name = "myCourseNo")
    private Long myCourseNo;

    @Column(name = "userNo")
    private Long userNo;

    @Column(name = "writtenTime")
    private LocalDateTime writtenTime;
}
