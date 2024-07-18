package com.multicampus.gangwonActivity.dto.request.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckPasswordRequestDto {
    @NonNull
    private String userPassword;
}
