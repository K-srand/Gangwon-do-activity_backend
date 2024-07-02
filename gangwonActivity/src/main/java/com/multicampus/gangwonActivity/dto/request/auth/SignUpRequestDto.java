package com.multicampus.gangwonActivity.dto.request.auth;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {


    private String userName;
    private String userId;
    private String userEmail;
    private String userPassword;
    private String userNick;
    private Boolean userExit;
    private Boolean userBan;
    private String userRole;




}
