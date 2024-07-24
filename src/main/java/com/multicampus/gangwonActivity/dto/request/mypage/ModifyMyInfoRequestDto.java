package com.multicampus.gangwonActivity.dto.request.mypage;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModifyMyInfoRequestDto {

    @NonNull
    private String userId;

    private String userPassword;
    private String userNick;

}
