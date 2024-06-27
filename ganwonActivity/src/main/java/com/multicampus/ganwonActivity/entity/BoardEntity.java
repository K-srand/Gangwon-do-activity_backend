package com.multicampus.ganwonActivity.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
    private boolean hasImage;
    private LocalDateTime writtenTime;
    private boolean isReported;
    private Long myCourseNo;






}
