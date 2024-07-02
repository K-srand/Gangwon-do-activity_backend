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
public class BoardImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageNo;

//    @ManyToOne
//    @JoinColumn(name="boardNo") // foreign key (boardNo)
    private Long boardNo;

    private String imageAddress;

}
