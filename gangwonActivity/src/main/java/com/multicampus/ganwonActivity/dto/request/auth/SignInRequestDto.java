package com.multicampus.ganwonActivity.dto.request.auth;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDto {
    @NonNull
    private String userId;
    @NonNull
    private String userPassword;
}
