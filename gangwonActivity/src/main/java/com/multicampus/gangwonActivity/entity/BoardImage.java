package com.multicampus.gangwonActivity.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="boardImage")
@Table(name="boardImage")
@Builder
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardImageNo;

    private Long boardNo;

    private String imageAddress;

}
