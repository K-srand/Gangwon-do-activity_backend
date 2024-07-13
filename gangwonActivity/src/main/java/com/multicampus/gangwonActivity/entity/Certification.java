package com.multicampus.gangwonActivity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="certification")
@Table(name="certification")
public class Certification {


    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "certificationNumber", nullable = false)
    private String certificationNumber;


}
