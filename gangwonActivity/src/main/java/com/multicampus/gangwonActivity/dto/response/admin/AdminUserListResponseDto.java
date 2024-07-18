package com.multicampus.gangwonActivity.dto.response.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserListResponseDto {

    private int userNo;
    private String userName;
    private String userId;
    private String userNick;
    private String email;

}
