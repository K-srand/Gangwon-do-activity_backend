package com.multicampus.ganwonActivity.entity;


import com.multicampus.ganwonActivity.dto.request.auth.SignUpRequestDto;
import com.multicampus.ganwonActivity.dto.response.auth.SignUpResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //=auto_Increment
    @Column(name="userNo")
    private Long userNo;

    private String userName;
    private String userId;
    private String userEmail;
    @Column(name = "userPassword", length = 255)
    private String userPassword;
    private String userNick;
    private Boolean userExit;
    private Boolean userBan;
    private String userRole;

//    LocalDateTime now = LocalDateTime.now().format((DateTimeFormatter.ofPattern("yyyy-MM-dd HH:~~~~`")));



    public UserEntity(SignUpRequestDto dto){

        this.userName = dto.getUserName();
        this.userId = dto.getUserId();
        this.userEmail = dto.getUserEmail();
        this.userPassword = dto.getUserPassword();
        this.userNick = dto.getUserNick();
        this.userExit = dto.getUserExit();
        this.userBan = dto.getUserBan();
        this.userRole = dto.getUserRole();
    }

}
