package com.multicampus.gangwonActivity.entity;


import com.multicampus.gangwonActivity.dto.request.auth.SignUpRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class User {
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
    private LocalDateTime userExitTime;
    private LocalDateTime userBanTime;
    private String userRole;    //ROLE_USER or ROLE_ADMIN
    private LocalDateTime registerTime;

//    LocalDateTime now = LocalDateTime.now().format((DateTimeFormatter.ofPattern("yyyy-MM-dd HH:~~~~`")));


    public User(SignUpRequestDto dto){

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        this.userName = dto.getUserName();
        this.userId = dto.getUserId();
        this.userEmail = dto.getUserEmail();
        this.userPassword = dto.getUserPassword();
        this.userNick = dto.getUserNick();
        this.userExitTime = null;
        this.userBanTime = null;
        this.userRole = "ROLE_USER";
        this.registerTime = localDateTime;
    }

    public void TempPassword(String tempPassword){

        this.userPassword = tempPassword;

    }

}
