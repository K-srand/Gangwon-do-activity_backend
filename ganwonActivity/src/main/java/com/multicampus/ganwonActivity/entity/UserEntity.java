package com.multicampus.ganwonActivity.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    private String userName;
    private String userId;
    private String userEmail;
    private String userPassword;
    private String userNick;
    private Boolean userExit;
    private Boolean userBan;
    private String userRole;

}
