package com.multicampus.gangwonActivity.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckCertificationRequestDto {

    @Email
    @NotBlank
    private String email;

//    @NotBlank
    private String certificationNumber;

    //아이디 찾을 때
    private String userName;
    //패스워드 찾을 때
    private String userId;


}
