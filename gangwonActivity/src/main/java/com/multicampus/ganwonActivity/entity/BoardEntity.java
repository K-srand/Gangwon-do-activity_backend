package com.multicampus.ganwonActivity.entity;


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
    private boolean hasImage;
    private LocalDateTime writtenTime;
    private boolean isReported;
    private Long myCourseNo;




}
