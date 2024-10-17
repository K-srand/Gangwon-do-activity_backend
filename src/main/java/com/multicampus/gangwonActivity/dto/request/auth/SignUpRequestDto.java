package com.multicampus.gangwonActivity.dto.request.auth;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    //유저가 입력하는 값
    private String userName;
    private String userId;
    private String userEmail;
    private String userPassword;
    private String userNick;

    // 자동생성되는 값 or default가 null인 녀석들
    private LocalDateTime userExitTime;
    private LocalDateTime userBanTime;
    private String userRole;
    private String type;



}
