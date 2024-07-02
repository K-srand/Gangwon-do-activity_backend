package com.multicampus.gangwonActivity.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board")
@Table(name = "board")
@Builder
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;
    private Long userNo;
    private String boardTitle;
    private String content;
    private int countLikes;
    private LocalDateTime writtenTime;
    private LocalDateTime deletedTime;
    private boolean isReported;
    private Long myCourseNo;
    private int boardCount;




}
