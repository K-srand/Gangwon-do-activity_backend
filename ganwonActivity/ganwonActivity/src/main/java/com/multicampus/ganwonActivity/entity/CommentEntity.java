package com.multicampus.ganwonActivity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="comment")
@Table(name="comment")
@Builder
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNo;
    private Long boardNo;
    private Long userNo;
    private LocalDateTime writtenTime;
    private String content;



}
