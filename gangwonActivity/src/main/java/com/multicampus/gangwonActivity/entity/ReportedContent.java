package com.multicampus.gangwonActivity.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity(name = "reportedContent")
@Table(name = "reportedContent")
//@NoArgsConstructor
//@RequiredArgsConstructor
@Builder
public class ReportedContent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportedContentNo;
    private Long userNo;
    private LocalDateTime reportedTime;
    private Long commentNo;
    private Long boardNo;
}


