package com.multicampus.gangwonActivity.service;

import com.multicampus.gangwonActivity.dto.response.user.GetSignInUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String id);


}
